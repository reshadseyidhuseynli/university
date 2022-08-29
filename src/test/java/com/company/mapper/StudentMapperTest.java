package com.company.mapper;

import com.company.domain.Grade;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.entity.ExamResult;
import com.company.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StudentMapperTest {

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    private static Student student;
    private static StudentResponseDto studentResponseDto;

    @BeforeAll
    private static void init() {
        student = new Student();
        student.setId(1);
        student.setName("testStudentName");
        student.setSurname("testStudentSurname");
        student.setBirthdate(LocalDate.of(1997, 4, 1));
        student.setGrade(Grade.BACHELOR_I);
        student.setExamResults(new ArrayList<>(Collections.singletonList(new ExamResult())));

        studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(1);
        studentResponseDto.setName("testStudentName");
        studentResponseDto.setSurname("testStudentSurname");
        studentResponseDto.setBirthdate(LocalDate.of(1997, 4, 1));
        studentResponseDto.setGrade(Grade.BACHELOR_I);
        studentResponseDto.setExamResults(new ArrayList<>(Collections.singletonList(new ExamResultResponseDto())));
    }

    @Test
    void toStudentDtoTest() {
        StudentResponseDto actual = studentMapper.toStudentDto(student);

        Assertions.assertEquals(studentResponseDto, actual);
    }

    @Test
    void toStudentDtoListTest() {
        List<Student> given = new ArrayList<>();
        given.add(student);

        List<StudentResponseDto> expected = new ArrayList<>();
        expected.add(studentResponseDto);

        List<StudentResponseDto> actual = studentMapper.toStudentDtoList(given);

        Assertions.assertEquals(expected, actual);
    }
}
