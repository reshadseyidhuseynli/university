package com.company.service;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.entity.ExamResult;
import com.company.entity.Subject;
import com.company.entity.Teacher;
import com.company.entity.Student;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.mapper.StudentMapper;
import com.company.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private ExamResultRepository examResultRepository;
    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private ExamResultMapper examResultMapper;
    @InjectMocks
    private StudentService studentService;

    @Mock
    private static ExamResult examResult;

    @BeforeAll
    private static void init() {
        examResult = new ExamResult();
    }

    @Test
    void getAllTest() {
        List<Student> studentList = new ArrayList<>();
        List<StudentResponseDto> expected = new ArrayList<>();

        Mockito.when(studentRepository.findAll())
                .thenReturn(studentList);
        Mockito.when(studentMapper.toStudentDtoList(studentList))
                .thenReturn(expected);

        List<StudentResponseDto> actual = studentService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(studentRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudentDtoList(studentList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;
        Student student = new Student();
        StudentResponseDto expected = new StudentResponseDto();

        Mockito.when(studentRepository.findById(given))
                .thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentDto(student))
                .thenReturn(expected);

        StudentResponseDto actual = studentService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudentDto(student);
    }

    @Test
    void getByIdNotFoundTest() {
        Integer given = 100;
        Student student = new Student();

        Mockito.when(studentRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> studentService.getById(given));
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(studentMapper, Mockito.never())
                .toStudentDto(student);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        studentService.delete(given);
        Mockito.verify(studentRepository, Mockito.times(1))
                .deleteById(given);
    }

    @Test
    void addExamResultToStudentTest() {
        Integer givenId = 1;
        ExamResultRequestDto givenDto = new ExamResultRequestDto();
        givenDto.setSubjectId(2);
        ExamResultResponseDto expected = new ExamResultResponseDto();

        Student student = new Student();
        Subject subject = new Subject();

        Mockito.when(studentRepository.findById(givenId))
                .thenReturn(Optional.of(student));
        Mockito.when(subjectRepository.findById(givenDto.getSubjectId()))
                .thenReturn(Optional.of(subject));
        Mockito.when(examResultMapper.toExamResult(givenDto))
                .thenReturn(examResult);
        Mockito.when(examResultMapper.toExamResultDto(examResult))
                .thenReturn(expected);

        ExamResultResponseDto actual =
                studentService.addExamResultForStudent(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(subjectRepository, Mockito.times(1))
                .findById(givenDto.getSubjectId());
        Mockito.verify(examResultMapper, Mockito.times(1))
                .toExamResult(givenDto);
        Mockito.verify(examResult, Mockito.times(1))
                .setStudent(student);
        Mockito.verify(examResult, Mockito.times(1))
                .setSubject(subject);
        Mockito.verify(examResultRepository, Mockito.times(1))
                .save(examResult);
        Mockito.verify(examResultMapper, Mockito.times(1))
                .toExamResultDto(examResult);
    }
}
