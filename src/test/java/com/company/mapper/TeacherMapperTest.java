package com.company.mapper;

import com.company.domain.AcademicRank;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.StudentListDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Lesson;
import com.company.entity.Teacher;
import com.company.entity.TeacherStudent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TeacherMapperTest {

    private final TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

    private static Teacher teacher;
    private static TeacherResponseDto teacherResponseDto;

    @BeforeAll
    private static void init() {
        teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("testTeacherName");
        teacher.setSurname("testTeacherSurname");
        teacher.setBirthdate(LocalDate.of(1980, 1, 1));
        teacher.setAcademicRank(AcademicRank.PROFESSOR);
        teacher.setLesson(new Lesson());
        TeacherStudent teacherStudent = new TeacherStudent();
        teacherStudent.setTeacher(teacher);
        teacher.setStudents(new ArrayList<>(Collections.singletonList(teacherStudent)));

        teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setId(1);
        teacherResponseDto.setName("testTeacherName");
        teacherResponseDto.setSurname("testTeacherSurname");
        teacherResponseDto.setBirthdate(LocalDate.of(1980, 1, 1));
        teacherResponseDto.setAcademicRank(AcademicRank.PROFESSOR);
        teacherResponseDto.setLesson(new LessonResponseDto());
        teacherResponseDto.setStudents(new ArrayList<>(
                Collections.singletonList(new StudentListDto())));
    }

    @Test
    void toTeacherDtoTest() {
        TeacherResponseDto actual = teacherMapper.toTeacherDto(teacher);

        Assertions.assertEquals(teacherResponseDto, actual);
    }

    @Test
    void toTeacherDtoListTest() {
        List<Teacher> given = new ArrayList<>();
        given.add(teacher);

        List<TeacherResponseDto> expected = new ArrayList<>();
        expected.add(teacherResponseDto);

        List<TeacherResponseDto> actual = teacherMapper.toTeacherDtoList(given);

        Assertions.assertEquals(expected, actual);
    }
}
