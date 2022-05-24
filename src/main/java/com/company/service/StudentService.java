package com.company.service;

import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.request.StudentRequestDto;
import com.company.entity.ExamResult;
import com.company.entity.Faculty;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.mapper.StudentMapper;
import com.company.repository.ExamResultRepository;
import com.company.repository.FacultyRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ExamResultRepository examResultRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;
    private final ExamResultMapper examResultMapper;

    public List<StudentResponseDto> getAll() {
        return studentMapper.toStudentDtoList(studentRepository.findAll());
    }

    public StudentResponseDto getById(Integer id) {
        return studentMapper.toStudentDto(findByIdIfAvailable(id));
    }

    public StudentResponseDto delete(Integer id) {
        final Student student = findByIdIfAvailable(id);
        studentRepository.delete(student);

        return studentMapper.toStudentDto(student);
    }

    public StudentResponseDto add(Integer facultyId,
                                  StudentRequestDto requestDto) {
        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.FACULTY_NOT_FOUND,
                        "Faculty not found, by id: " + facultyId));

        final Student student = new Student(
                faculty,
                requestDto.getName(),
                requestDto.getSurname(),
                requestDto.getBirthdate());

        studentRepository.save(student);

        return studentMapper.toStudentDto(student);
    }

    public ExamResultResponseDto addExamResultToStudent(Integer studentId,
                                                        ExamResultRequestDto requestDto) {
        final Student student = findByIdIfAvailable(studentId);

        final Integer teacherId = requestDto.getTeacherId();

        final Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.TEACHER_NOT_FOUND,
                        "Teacher not found, by id: " + teacherId));

        ExamResult examResult = new ExamResult(
                student,
                teacher,
                requestDto.getTrueAnswerCount(),
                requestDto.getFalseAnswerCount());

        examResultRepository.save(examResult);

        return examResultMapper.toExamResultDto(examResult);
    }

    private Student findByIdIfAvailable(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.STUDENT_NOT_FOUND,
                        "Student not found, by id: " + id));
    }

}
