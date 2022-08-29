package com.company.service;

import com.company.dto.request.FacultyRequestDto;
import com.company.dto.request.LessonRequestDto;
import com.company.dto.request.StudentRequestDto;
import com.company.dto.request.TeacherRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Faculty;
import com.company.entity.Lesson;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.mapper.FacultyMapper;
import com.company.mapper.LessonMapper;
import com.company.mapper.StudentMapper;
import com.company.mapper.TeacherMapper;
import com.company.repository.FacultyRepository;
import com.company.repository.LessonRepository;
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
class FacultyServiceTest {

    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private FacultyMapper facultyMapper;
    @Mock
    private LessonMapper lessonMapper;
    @Mock
    private TeacherMapper teacherMapper;
    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private FacultyService facultyService;

    private static Faculty faculty;

    @BeforeAll
    private static void init() {
        faculty = new Faculty();
    }

    @Test
    void getAllTest() {
        List<Faculty> facultyList = new ArrayList<>();
        List<FacultyResponseDto> expected = new ArrayList<>();

        Mockito.when(facultyRepository.findAll())
                .thenReturn(facultyList);
        Mockito.when(facultyMapper.toFacultyDtoList(facultyList))
                .thenReturn(expected);

        List<FacultyResponseDto> actual = facultyService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(facultyMapper, Mockito.times(1))
                .toFacultyDtoList(facultyList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;

        FacultyResponseDto expected = new FacultyResponseDto();

        Mockito.when(facultyRepository.findById(given))
                .thenReturn(Optional.of(faculty));
        Mockito.when(facultyMapper.toFacultyDto(faculty))
                .thenReturn(expected);

        FacultyResponseDto actual = facultyService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(facultyMapper, Mockito.times(1))
                .toFacultyDto(faculty);
    }

    @Test
    void getByIdNotFoundFacultyTest() {
        Integer given = 100;

        Mockito.when(facultyRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class, () -> facultyService.getById(given));
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(facultyMapper, Mockito.never())
                .toFacultyDto(faculty);
    }

    @Test
    void deleteTest() {
        Integer given = 1;

        FacultyResponseDto expected = new FacultyResponseDto();

        Mockito.when(facultyRepository.findById(given))
                .thenReturn(Optional.of(faculty));
        Mockito.when(facultyMapper.toFacultyDto(faculty))
                .thenReturn(expected);

        FacultyResponseDto actual = facultyService.delete(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .delete(faculty);
        Mockito.verify(facultyMapper, Mockito.times(1))
                .toFacultyDto(faculty);
    }

    @Test
    void deleteNotFoundFacultyTest() {
        Integer given = 100;

        Mockito.when(facultyRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class, () -> facultyService.delete(given));
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(facultyRepository, Mockito.never())
                .delete(faculty);
        Mockito.verify(facultyMapper, Mockito.never())
                .toFacultyDto(faculty);
    }

    @Test
    void addTest() {
        FacultyRequestDto given = new FacultyRequestDto();
        FacultyResponseDto expected = new FacultyResponseDto();

        Mockito.when(facultyMapper.toFaculty(given))
                .thenReturn(faculty);
        Mockito.when(facultyRepository.save(faculty))
                .thenReturn(faculty);
        Mockito.when(facultyMapper.toFacultyDto(faculty))
                .thenReturn(expected);

        FacultyResponseDto actual = facultyService.add(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(facultyMapper, Mockito.times(1))
                .toFaculty(given);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .save(faculty);
        Mockito.verify(facultyMapper, Mockito.times(1))
                .toFacultyDto(faculty);
    }

    @Test
    void addLessonToFacultyTest() {
        Integer givenId = 1;
        LessonRequestDto givenDto = new LessonRequestDto();
        LessonResponseDto expected = new LessonResponseDto();

        Lesson lesson = new Lesson();
        lesson.setFaculty(faculty);

        Mockito.when(facultyRepository.findById(givenId))
                .thenReturn(Optional.of(faculty));
        Mockito.when(lessonMapper.toLesson(givenDto))
                .thenReturn(lesson);
        Mockito.when(lessonRepository.save(lesson))
                .thenReturn(lesson);
        Mockito.when(lessonMapper.toLessonDto(lesson))
                .thenReturn(expected);

        LessonResponseDto actual =
                facultyService.addLessonToFaculty(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(lessonMapper, Mockito.times(1))
                .toLesson(givenDto);
        Mockito.verify(lessonRepository, Mockito.times(1))
                .save(lesson);
        Mockito.verify(lessonMapper, Mockito.times(1))
                .toLessonDto(lesson);
    }

    @Test
    void addLessonToFacultyNotFoundFacultyTest() {
        Integer givenId = 100;
        LessonRequestDto givenDto = new LessonRequestDto();

        Lesson lesson = new Lesson();

        Mockito.when(facultyRepository.findById(givenId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> facultyService.addLessonToFaculty(givenId, givenDto));
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(lessonMapper, Mockito.never())
                .toLesson(givenDto);
        Mockito.verify(lessonRepository, Mockito.never())
                .save(lesson);
        Mockito.verify(lessonMapper, Mockito.never())
                .toLessonDto(lesson);
    }

    @Test
    void addTeacherToFacultyTest() {
        Integer givenId = 1;
        TeacherRequestDto givenDto = new TeacherRequestDto();
        TeacherResponseDto expected = new TeacherResponseDto();

        Teacher teacher = new Teacher();
        teacher.setFaculty(faculty);

        Mockito.when(facultyRepository.findById(givenId))
                .thenReturn(Optional.of(faculty));
        Mockito.when(teacherMapper.toTeacher(givenDto))
                .thenReturn(teacher);
        Mockito.when(teacherRepository.save(teacher))
                .thenReturn(teacher);
        Mockito.when(teacherMapper.toTeacherDto(teacher))
                .thenReturn(expected);

        TeacherResponseDto actual =
                facultyService.addTeacherToFaculty(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(teacherMapper, Mockito.times(1))
                .toTeacher(givenDto);
        Mockito.verify(teacherRepository, Mockito.times(1))
                .save(teacher);
        Mockito.verify(teacherMapper, Mockito.times(1))
                .toTeacherDto(teacher);
    }

    @Test
    void addTeacherToFacultyNotFoundFacultyTest() {
        Integer givenId = 100;
        TeacherRequestDto givenDto = new TeacherRequestDto();

        Teacher teacher = new Teacher();

        Mockito.when(facultyRepository.findById(givenId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> facultyService.addTeacherToFaculty(givenId, givenDto));
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(teacherMapper, Mockito.never())
                .toTeacher(givenDto);
        Mockito.verify(teacherRepository, Mockito.never())
                .save(teacher);
        Mockito.verify(teacherMapper, Mockito.never())
                .toTeacherDto(teacher);
    }

    @Test
    void addStudentToFacultyTest() {
        Integer givenId = 1;
        StudentRequestDto givenDto = new StudentRequestDto();
        StudentResponseDto expected = new StudentResponseDto();

        Student student = new Student();
        student.setFaculty(faculty);

        Mockito.when(facultyRepository.findById(givenId))
                .thenReturn(Optional.of(faculty));
        Mockito.when(studentMapper.toStudent(givenDto))
                .thenReturn(student);
        Mockito.when(studentRepository.save(student))
                .thenReturn(student);
        Mockito.when(studentMapper.toStudentDto(student))
                .thenReturn(expected);

        StudentResponseDto actual =
                facultyService.addStudentToFaculty(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudent(givenDto);
        Mockito.verify(studentRepository, Mockito.times(1))
                .save(student);
        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudentDto(student);
    }

    @Test
    void addStudentToFacultyNotFoundFacultyTest() {
        Integer givenId = 100;
        StudentRequestDto givenDto = new StudentRequestDto();

        Student student = new Student();

        Mockito.when(facultyRepository.findById(givenId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> facultyService.addStudentToFaculty(givenId, givenDto));
        Mockito.verify(facultyRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(studentMapper, Mockito.never())
                .toStudent(givenDto);
        Mockito.verify(studentRepository, Mockito.never())
                .save(student);
        Mockito.verify(studentMapper, Mockito.never())
                .toStudentDto(student);
    }
}
