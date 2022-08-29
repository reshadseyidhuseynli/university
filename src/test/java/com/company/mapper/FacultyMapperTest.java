package com.company.mapper;

import com.company.domain.AcademicRank;
import com.company.domain.Grade;
import com.company.dto.request.FacultyRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Faculty;
import com.company.entity.Lesson;
import com.company.entity.Student;
import com.company.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FacultyMapperTest {

    private final FacultyMapper facultyMapper = FacultyMapper.INSTANCE;

    private static Faculty faculty;
    private static FacultyResponseDto facultyResponseDto;

    @BeforeAll
    private static void init() {
        faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("testFaculty");
        Teacher teacher = new Teacher();
        teacher.setAcademicRank(AcademicRank.PROFESSOR);
        faculty.setHead(teacher);
        faculty.setLessons(new ArrayList<>(Collections.singletonList(new Lesson())));
        faculty.setTeachers(new ArrayList<>(Collections.singletonList(new Teacher())));
        faculty.setStudents(new ArrayList<>(Collections.singletonList(new Student())));

        facultyResponseDto = new FacultyResponseDto();
        facultyResponseDto.setId(1);
        facultyResponseDto.setName("testFaculty");
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setAcademicRank(AcademicRank.PROFESSOR);
        facultyResponseDto.setHead(teacherResponseDto);
        facultyResponseDto.setLessons(new ArrayList<>(Collections.singletonList(new LessonResponseDto())));
        facultyResponseDto.setTeachers(new ArrayList<>(Collections.singletonList(new TeacherResponseDto())));
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setGrade(Grade.BACHELOR_I);
        facultyResponseDto.setStudents(new ArrayList<>(Collections.singletonList(studentResponseDto)));
    }

    @Test
    void toFacultyResponseDtoTest() {
        FacultyResponseDto actual = facultyMapper.toFacultyDto(faculty);

        Assertions.assertEquals(facultyResponseDto, actual);
    }

    @Test
    void toFacultyResponseDtoListTest() {
        List<Faculty> given = new ArrayList<>();
        given.add(faculty);

        List<FacultyResponseDto> expected = new ArrayList<>();
        expected.add(facultyResponseDto);

        List<FacultyResponseDto> actual = facultyMapper.toFacultyDtoList(given);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toFacultyTest() {
        FacultyRequestDto given = new FacultyRequestDto();
        given.setName("testFaculty");
        Faculty expected = new Faculty();
        expected.setId(1000);
        expected.setName("testFaculty");

        Faculty actual = facultyMapper.toFaculty(given);
        actual.setId(1000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toFacultyListTest() {
        FacultyRequestDto facultyRequestDto = new FacultyRequestDto();
        facultyRequestDto.setName("testFaculty");
        Faculty faculty = new Faculty();
        faculty.setId(1000);
        faculty.setName("testFaculty");

        List<FacultyRequestDto> given = new ArrayList<>();
        given.add(facultyRequestDto);

        List<Faculty> expected = new ArrayList<>();
        expected.add(faculty);

        List<Faculty> actual = facultyMapper.toFacultyList(given);
        actual.get(0).setId(1000);

        Assertions.assertEquals(expected, actual);
    }
}
