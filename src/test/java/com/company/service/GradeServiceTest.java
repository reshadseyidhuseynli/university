package com.company.service;

import com.company.domain.Grade;
import com.company.entity.Student;
import com.company.error.ServiceException;
import com.company.repository.StudentRepository;
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
class GradeServiceTest {

    @Mock
    private static Student student;
    private final Grade grade = Grade.BACHELOR_I;

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private GradeService gradeService;

    @BeforeAll
    private static void init() {
        student = new Student();
    }

    @Test
    void passNextYearTest() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);

        Grade grade2 = Grade.BACHELOR_II;

        Mockito.when(studentRepository.findAll())
                .thenReturn(studentList);
        Mockito.when(student.getGrade())
                .thenReturn(grade);

        gradeService.passNextYear();

        Mockito.verify(studentRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(student, Mockito.times(studentList.size()))
                .getGrade();
        Mockito.verify(student, Mockito.times(studentList.size()))
                .setGrade(grade2);
        Mockito.verify(studentRepository, Mockito.times(studentList.size()))
                .save(student);
    }

    @Test
    void passNextYearByStudentIdTest() {
        Integer givenId = 1;
        Integer givenYear = 2;

        Grade grade3 = Grade.BACHELOR_III;

        Mockito.when(studentRepository.findById(givenId))
                .thenReturn(Optional.of(student));
        Mockito.when(student.getGrade())
                .thenReturn(grade);

        gradeService.passNextYearByStudentId(givenId, givenYear);

        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(student, Mockito.times(1))
                .getGrade();
        Mockito.verify(student, Mockito.times(1))
                .setGrade(grade3);
        Mockito.verify(studentRepository, Mockito.times(1))
                .save(student);
    }

    @Test
    void passNextYearByStudentIdYearIsNullTest() {
        Integer givenId = 1;
        Integer givenYear = null;

        Grade grade2 = Grade.BACHELOR_II;

        Mockito.when(studentRepository.findById(givenId))
                .thenReturn(Optional.of(student));
        Mockito.when(student.getGrade())
                .thenReturn(grade);

        gradeService.passNextYearByStudentId(givenId, givenYear);

        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(student, Mockito.times(1))
                .getGrade();
        Mockito.verify(student, Mockito.times(1))
                .setGrade(grade2);
        Mockito.verify(studentRepository, Mockito.times(1))
                .save(student);
    }

    @Test
    void passNextYearByStudentIdNotFoundStudentTest() {
        Integer givenId = 100;
        Integer givenYear = 2;

        Grade grade3 = Grade.BACHELOR_III;

        Mockito.when(studentRepository.findById(givenId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> gradeService.passNextYearByStudentId(givenId, givenYear));
        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(student, Mockito.never())
                .getGrade();
        Mockito.verify(student, Mockito.never())
                .setGrade(grade3);
        Mockito.verify(studentRepository, Mockito.never())
                .save(student);
    }
}
