package com.company.mapper;

import com.company.domain.AcademicRank;
import com.company.dto.response.StudentListDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Subject;
import com.company.entity.Teacher;
import com.company.entity.TeacherStudent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherMapperTest {

    private static final Integer ID = 1;
    private static final String TEACHER_NAME = "testTeacherName";
    private static final String TEACHER_SURNAME = "testTeacherSurname";

    private static Teacher teacher;
    private static TeacherResponseDto teacherResponseDto;

    private final TeacherMapper teacherMapper = TeacherMapper.INSTANCE;

    @BeforeAll
    public static void init() {
        teacher = new Teacher();
        teacher.setId(ID);
        teacher.setName(TEACHER_NAME);
        teacher.setSurname(TEACHER_SURNAME);
        teacher.setBirthdate(LocalDate.of(1980, 1, 1));
        teacher.setAcademicRank(AcademicRank.PROFESSOR);
        teacher.setSubject(new Subject());
        TeacherStudent teacherStudent = new TeacherStudent();
        teacherStudent.setTeacher(teacher);
        teacher.setStudents(new ArrayList<>(Collections.singletonList(teacherStudent)));

        teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setId(ID);
        teacherResponseDto.setName(TEACHER_NAME);
        teacherResponseDto.setSurname(TEACHER_SURNAME);
        teacherResponseDto.setBirthdate(LocalDate.of(1980, 1, 1));
        teacherResponseDto.setAcademicRank(AcademicRank.PROFESSOR);
        teacherResponseDto.setSubject(new SubjectResponseDto());
        teacherResponseDto.setStudents(new ArrayList<>(
                Collections.singletonList(new StudentListDto())));
    }

    @Test
    void toTeacherDtoTest() {
        Teacher given = teacher;
        TeacherResponseDto expected = teacherResponseDto;
        TeacherResponseDto actual = teacherMapper.toTeacherDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toTeacherDtoListTest() {
        List<Teacher> given = Collections.singletonList(teacher);
        List<TeacherResponseDto> expected = Collections.singletonList(teacherResponseDto);
        List<TeacherResponseDto> actual = teacherMapper.toTeacherDtoList(given);
        assertEquals(expected, actual);
    }
}
