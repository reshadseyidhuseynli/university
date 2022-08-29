package com.company.service;

import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.entity.TeacherStudent;
import com.company.error.ServiceException;
import com.company.mapper.StudentMapper;
import com.company.mapper.TeacherMapper;
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
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private TeacherMapper teacherMapper;
    @Mock
    private StudentMapper studentMapper;
    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private static Teacher teacher;
    @Mock
    private static Student student;
    @Mock
    private static TeacherStudent teacherStudent;

    @BeforeAll
    private static void init() {
        teacher = new Teacher();
        student = new Student();
        teacherStudent = new TeacherStudent();
    }


    @Test
    void getAllTest() {
        List<Teacher> teacherList = new ArrayList<>();
        List<TeacherResponseDto> expected = new ArrayList<>();

        Mockito.when(teacherRepository.findAll())
                .thenReturn(teacherList);
        Mockito.when(teacherMapper.toTeacherDtoList(teacherList))
                .thenReturn(expected);

        List<TeacherResponseDto> actual = teacherService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(teacherMapper, Mockito.times(1))
                .toTeacherDtoList(teacherList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;
        Teacher teacher = new Teacher();
        TeacherResponseDto expected = new TeacherResponseDto();

        Mockito.when(teacherRepository.findById(given))
                .thenReturn(Optional.of(teacher));
        Mockito.when(teacherMapper.toTeacherDto(teacher))
                .thenReturn(expected);

        TeacherResponseDto actual = teacherService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(teacherMapper, Mockito.times(1))
                .toTeacherDto(teacher);
    }

    @Test
    void getByIdNotFoundTest() {
        Integer given = 100;
        Teacher teacher = new Teacher();

        Mockito.when(teacherRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> teacherService.getById(given));
        Mockito.verify(teacherRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(teacherMapper, Mockito.never())
                .toTeacherDto(teacher);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        Teacher teacher = new Teacher();
        TeacherResponseDto expected = new TeacherResponseDto();

        Mockito.when(teacherRepository.findById(given))
                .thenReturn(Optional.of(teacher));
        Mockito.when(teacherMapper.toTeacherDto(teacher))
                .thenReturn(expected);

        TeacherResponseDto actual = teacherService.delete(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .delete(teacher);
        Mockito.verify(teacherMapper, Mockito.times(1))
                .toTeacherDto(teacher);
    }

    @Test
    void deleteNotFoundTest() {
        Integer given = 100;
        Teacher teacher = new Teacher();

        Mockito.when(teacherRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> teacherService.delete(given));
        Mockito.verify(teacherRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(teacherRepository, Mockito.never())
                .delete(teacher);
        Mockito.verify(teacherMapper, Mockito.never())
                .toTeacherDto(teacher);
    }
}
