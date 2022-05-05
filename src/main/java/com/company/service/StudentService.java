package com.company.service;

import com.company.dto.ExamResultDto;
import com.company.dto.StudentDto;
import com.company.entity.ExamResult;
import com.company.entity.Faculty;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.mapper.StudentMapper;
import com.company.repository.ExamResultRepository;
import com.company.repository.FacultyRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ExamResultRepository examResultRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper studentMapper;
    private final ExamResultMapper examResultMapper;

    StudentService(StudentRepository studentRepository,
                   TeacherRepository teacherRepository,
                   ExamResultRepository examResultRepository,
                   FacultyRepository facultyRepository,
                   StudentMapper studentMapper,
                   ExamResultMapper examResultMapper) {

        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.examResultRepository = examResultRepository;
        this.facultyRepository = facultyRepository;
        this.studentMapper = studentMapper;
        this.examResultMapper = examResultMapper;
    }

    public List<StudentDto> getAll() {

        return studentMapper.toStudentDtoList(studentRepository.findAll());
    }

    public StudentDto getById(Integer id) {

        return studentMapper.toStudentDto(studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Student not found, by id: " + id)));
    }

    public StudentDto delete(Integer id) {

        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Student not found, by id: " + id));

        studentRepository.delete(student);

        return studentMapper.toStudentDto(student);

    }


    public StudentDto add(Integer facultyId,
                          String name,
                          String surname,
                          LocalDate birthdate) {

        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));

        final Student student = new Student(faculty, name, surname, birthdate);
        studentRepository.save(student);

        return studentMapper.toStudentDto(student);

    }

    public ExamResultDto addExamResultToStudent(Integer studentId,
                                                Integer teacherId,
                                                Integer trueAnswerCount,
                                                Integer falseAnswerCount) {

        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ServiceException("Student not found, by id: " + studentId));

        final Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ServiceException("Teacher not found, by id: " + teacherId));

        ExamResult examResult = new ExamResult(student, teacher, trueAnswerCount, falseAnswerCount);
        examResultRepository.save(examResult);

        return examResultMapper.toExamResultDto(examResult);

    }

}
