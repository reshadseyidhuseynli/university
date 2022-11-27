package com.company.mapper;

import com.company.domain.Grade;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.entity.ExamResult;
import com.company.entity.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentMapperTest {

    private static final Integer ID = 1;
    private static final String STUDENT_NAME = "testStudentName";
    private static final String STUDENT_SURNAME = "testStudentSurname";

    private static Student student;
    private static StudentResponseDto studentResponseDto;

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    @BeforeAll
    public static void init() {
        student = new Student();
        student.setId(ID);
        student.setName(STUDENT_NAME);
        student.setSurname(STUDENT_SURNAME);
        student.setBirthdate(LocalDate.of(1997, 4, 1));
        student.setGrade(Grade.BACHELOR_I);
        student.setExamResults(new ArrayList<>(Collections.singletonList(new ExamResult())));

        studentResponseDto = new StudentResponseDto();
        studentResponseDto.setId(ID);
        studentResponseDto.setName(STUDENT_NAME);
        studentResponseDto.setSurname(STUDENT_SURNAME);
        studentResponseDto.setBirthdate(LocalDate.of(1997, 4, 1));
        studentResponseDto.setGrade(Grade.BACHELOR_I);
        studentResponseDto.setExamResults(new ArrayList<>(Collections.singletonList(new ExamResultResponseDto())));
    }

    @Test
    void toStudentDtoTest() {
        Student given = student;
        StudentResponseDto expected = studentResponseDto;
        StudentResponseDto actual = studentMapper.toStudentDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toStudentDtoListTest() {
        List<Student> given = Collections.singletonList(student);
        List<StudentResponseDto> expected = Collections.singletonList(studentResponseDto);
        List<StudentResponseDto> actual = studentMapper.toStudentDtoList(given);
        assertEquals(expected, actual);
    }
}
