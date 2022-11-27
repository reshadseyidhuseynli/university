package com.company.mapper;

import com.company.domain.AcademicRank;
import com.company.domain.Grade;
import com.company.dto.request.FacultyRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Faculty;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.entity.Teacher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FacultyMapperTest {

    private static final Integer ID = 1;
    private static final String FACULTY_NAME = "testFaculty";

    private static Faculty faculty;
    private static FacultyResponseDto facultyResponseDto;
    private static FacultyRequestDto facultyRequestDto;
    private static Faculty newFaculty;

    private final FacultyMapper facultyMapper = FacultyMapper.INSTANCE;

    @BeforeAll
    public static void init() {
        faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(FACULTY_NAME);
        Teacher teacher = new Teacher();
        teacher.setAcademicRank(AcademicRank.PROFESSOR);
        faculty.setHead(teacher);
        faculty.setSubjects(new ArrayList<>(Collections.singletonList(new Subject())));
        faculty.setTeachers(new ArrayList<>(Collections.singletonList(new Teacher())));
        faculty.setStudents(new ArrayList<>(Collections.singletonList(new Student())));

        facultyResponseDto = new FacultyResponseDto();
        facultyResponseDto.setId(ID);
        facultyResponseDto.setName(FACULTY_NAME);
        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setAcademicRank(AcademicRank.PROFESSOR);
        facultyResponseDto.setHead(teacherResponseDto);
        facultyResponseDto.setSubjects(new ArrayList<>(Collections.singletonList(new SubjectResponseDto())));
        facultyResponseDto.setTeachers(new ArrayList<>(Collections.singletonList(new TeacherResponseDto())));
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setGrade(Grade.BACHELOR_I);
        facultyResponseDto.setStudents(new ArrayList<>(Collections.singletonList(studentResponseDto)));

        facultyRequestDto = new FacultyRequestDto();
        facultyRequestDto.setName(FACULTY_NAME);

        newFaculty = new Faculty();
        newFaculty.setId(ID);
        newFaculty.setName(FACULTY_NAME);
    }

    @Test
    void toFacultyResponseDtoTest() {
        Faculty given = faculty;
        FacultyResponseDto expected = facultyResponseDto;
        FacultyResponseDto actual = facultyMapper.toFacultyDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toFacultyTest() {
        FacultyRequestDto given = facultyRequestDto;
        Faculty expected = newFaculty;

        Faculty actual = facultyMapper.toFaculty(given);
        actual.setId(ID);
        assertEquals(expected, actual);
    }

    @Test
    void toFacultyResponseDtoListTest() {
        List<Faculty> given = Collections.singletonList(faculty);
        List<FacultyResponseDto> expected = Collections.singletonList(facultyResponseDto);
        List<FacultyResponseDto> actual = facultyMapper.toFacultyDtoList(given);
        assertEquals(expected, actual);
    }

    @Test
    void toFacultyListTest() {
        List<FacultyRequestDto> given = Collections.singletonList(facultyRequestDto);
        List<Faculty> expected = Collections.singletonList(newFaculty);

        List<Faculty> actual = facultyMapper.toFacultyList(given);
        actual.get(0).setId(ID);
        assertEquals(expected, actual);
    }
}
