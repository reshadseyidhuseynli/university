package com.company.service;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.entity.ExamResult;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.mapper.StudentMapper;
import com.company.repository.ExamResultRepository;
import com.company.repository.FacultyRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
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
    private TeacherRepository teacherRepository;
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
        Student student = new Student();
        StudentResponseDto expected = new StudentResponseDto();

        Mockito.when(studentRepository.findById(given))
                .thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentDto(student))
                .thenReturn(expected);

        StudentResponseDto actual = studentService.delete(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(studentRepository, Mockito.times(1))
                .delete(student);
        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudentDto(student);
    }

    @Test
    void deleteNotFoundTest() {
        Integer given = 100;
        Student student = new Student();

        Mockito.when(studentRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> studentService.delete(given));
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(studentRepository, Mockito.never())
                .delete(student);
        Mockito.verify(studentMapper, Mockito.never())
                .toStudentDto(student);
    }

    @Test
    void addExamResultToStudentTest() {
        Integer givenId = 1;
        ExamResultRequestDto givenDto = new ExamResultRequestDto();
        givenDto.setTeacherId(2);
        ExamResultResponseDto expected = new ExamResultResponseDto();

        Student student = new Student();
        Teacher teacher = new Teacher();

        Mockito.when(studentRepository.findById(givenId))
                .thenReturn(Optional.of(student));
        Mockito.when(teacherRepository.findById(givenDto.getTeacherId()))
                .thenReturn(Optional.of(teacher));
        Mockito.when(examResultMapper.toExamResult(givenDto))
                .thenReturn(examResult);
        Mockito.when(examResultMapper.toExamResultDto(examResult))
                .thenReturn(expected);

        ExamResultResponseDto actual =
                studentService.addExamResultToStudent(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .findById(givenDto.getTeacherId());
        Mockito.verify(examResultMapper, Mockito.times(1))
                .toExamResult(givenDto);
        Mockito.verify(examResult, Mockito.times(1))
                .setStudent(student);
        Mockito.verify(examResult, Mockito.times(1))
                .setTeacher(teacher);
        Mockito.verify(examResultRepository, Mockito.times(1))
                .save(examResult);
        Mockito.verify(examResultMapper, Mockito.times(1))
                .toExamResultDto(examResult);
    }
}
