package com.company.service;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.entity.ExamResult;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.mapper.StudentMapper;
import com.company.repository.ExamResultRepository;
import com.company.repository.StudentRepository;
import com.company.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ExamResultRepository examResultRepository;
    private final StudentMapper studentMapper;
    private final ExamResultMapper examResultMapper;

    public List<StudentResponseDto> getAll() {
        return studentMapper.toStudentDtoList(studentRepository.findAll());
    }

    public StudentResponseDto getById(Integer id) {
        return studentMapper.toStudentDto(findById(id));
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public ExamResultResponseDto addExamResultForStudent(Integer studentId,
                                                         ExamResultRequestDto requestDto) {
        Student student = findById(studentId);
        Integer subjectId = requestDto.getSubjectId();

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.TEACHER_NOT_FOUND,
                        "Teacher not found, by id: " + subjectId));

        ExamResult examResult = examResultMapper.toExamResult(requestDto);
        examResult.setStudent(student);
        examResult.setSubject(subject);

        examResultRepository.save(examResult);

        return examResultMapper.toExamResultDto(examResult);
    }

    private Student findById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.STUDENT_NOT_FOUND,
                        "Student not found, by id: " + id));
    }

}
