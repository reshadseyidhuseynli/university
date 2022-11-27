package com.company.service;

import com.company.entity.Teacher;
import com.company.entity.TeacherStudent;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Student;
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
    @Mock
    private static List<Student> studentList = new ArrayList<>();

    @BeforeAll
    private static void init() {
        teacher = new Teacher();
        student = new Student();
        teacherStudent = new TeacherStudent();
        studentList = new ArrayList<>();
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
        teacherService.delete(given);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .deleteById(given);
    }


//    @Test
//    void getStudentsOfTeacherByIdTest() {
//        Integer given = 1;
//        List<StudentResponseDto> expected = new ArrayList<>();
//
//        teacherStudent.setStudent(student);
//        teacherStudent.setTeacher(teacher);
//        List<TeacherStudent> teacherStudentList =
//                Collections.singletonList(teacherStudent);
//        teacher.setStudents(teacherStudentList);
//        studentList.add(student);
//
//        Mockito.when(teacherRepository.findById(given))
//                .thenReturn(Optional.of(teacher));
//        Mockito.when(teacher.getStudents())
//                .thenReturn(teacherStudentList);
//        Mockito.when(teacherStudent.getStudent())
//                .thenReturn(student);
//        Mockito.when(studentMapper.toStudentDtoList(studentList))
//                .thenReturn(expected);
//
//        List<StudentResponseDto> actual = teacherService.getStudentsOfTeacherById(given);
//
//        Assertions.assertEquals(expected, actual);
//        Mockito.verify(teacherRepository, Mockito.times(1))
//                .findById(given);
//        Mockito.verify(teacher, Mockito.times(1))
//                .getStudents();
//        Mockito.verify(teacherStudent, Mockito.times(teacherStudentList.size()))
//                .getStudent();
//        Mockito.verify(studentList, Mockito.times(teacherStudentList.size()))
//                .add(new Student());
//        Mockito.verify(studentMapper,Mockito.times(1))
//                .toStudentDtoList(studentList);
//    }
}
