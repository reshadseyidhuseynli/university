package com.company.controller;

import com.company.dto.request.FacultyRequestDto;
import com.company.dto.request.StudentRequestDto;
import com.company.dto.request.SubjectRequestDto;
import com.company.dto.request.TeacherRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerTest {

    private static final Integer ID = 1;
    private static final String FACULTY_NAME = "testFaculty";
    private static final String TEACHER_NAME = "testTeacher";
    private static final String SUBJECT_NAME = "testSubject";
    private static final String STUDENT_NAME = "testStudent";

    private static FacultyRequestDto facultyRequestDto;
    private static FacultyResponseDto facultyResponseDto;
    private static SubjectRequestDto subjectRequestDto;
    private static SubjectResponseDto subjectResponseDto;
    private static TeacherRequestDto teacherRequestDto;
    private static TeacherResponseDto teacherResponseDto;
    private static StudentRequestDto studentRequestDto;
    private static StudentResponseDto studentResponseDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    FacultyService facultyService;

    @BeforeAll
    static void init() {
        facultyResponseDto = new FacultyResponseDto();
        facultyResponseDto.setName(FACULTY_NAME);
        teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setName(TEACHER_NAME);
        facultyResponseDto.setHead(teacherResponseDto);
        List<TeacherResponseDto> teachers = Collections.singletonList(teacherResponseDto);
        facultyResponseDto.setTeachers(teachers);
        SubjectResponseDto subject = new SubjectResponseDto();
        subject.setName(SUBJECT_NAME);
        List<SubjectResponseDto> subjects = Collections.singletonList(subject);
        facultyResponseDto.setSubjects(subjects);
        studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(STUDENT_NAME);
        List<StudentResponseDto> students = Collections.singletonList(studentResponseDto);
        facultyResponseDto.setStudents(students);

        facultyRequestDto = new FacultyRequestDto();
        facultyRequestDto.setName(FACULTY_NAME);

        subjectRequestDto = new SubjectRequestDto();
        subjectRequestDto.setName(SUBJECT_NAME);
        subjectResponseDto = new SubjectResponseDto();
        subjectResponseDto.setName(SUBJECT_NAME);
        teacherRequestDto = new TeacherRequestDto();
        teacherRequestDto.setName(TEACHER_NAME);
        studentRequestDto = new StudentRequestDto();
        studentRequestDto.setName(STUDENT_NAME);
    }

    @Test
    void getAll_Succest() throws Exception {
        when(facultyService.getAll())
                .thenReturn(Collections.singletonList(facultyResponseDto));
        mockMvc.perform(get("/faculties"))
                .andExpect(jsonPath("$.[0].name", is(FACULTY_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).getAll();
    }

    @Test
    void getById_Succest() throws Exception {
        Integer given = ID;
        FacultyResponseDto expected = facultyResponseDto;
        when(facultyService.getById(given)).thenReturn(expected);
        mockMvc.perform(get("/faculties/{id}", given))
                .andExpect(jsonPath("$.name", is(FACULTY_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).getById(given);
    }

    @Test
    void getFacultyHeadById_Succest() throws Exception {
        Integer given = ID;
        when(facultyService.getById(given)).thenReturn(facultyResponseDto);
        mockMvc.perform(get("/faculties/{id}/head", given))
                .andExpect(jsonPath("$.name", is(TEACHER_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).getById(given);
    }

    @Test
    void getFacultySubjectsById_Succest() throws Exception {
        Integer given = ID;
        when(facultyService.getById(given)).thenReturn(facultyResponseDto);
        mockMvc.perform(get("/faculties/{id}/subjects", given))
                .andExpect(jsonPath("$.[0].name", is(SUBJECT_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).getById(given);
    }

    @Test
    void getFacultyTeachersById_Succest() throws Exception {
        Integer given = ID;
        when(facultyService.getById(given)).thenReturn(facultyResponseDto);
        mockMvc.perform(get("/faculties/{id}/teachers", given))
                .andExpect(jsonPath("$.[0].name", is(TEACHER_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).getById(given);
    }

    @Test
    void getFacultyStudentsById_Succest() throws Exception {
        Integer given = ID;
        List<StudentResponseDto> expected = facultyResponseDto.getStudents();

        when(facultyService.getById(given)).thenReturn(facultyResponseDto);
        mockMvc.perform(get("/faculties/{id}/students", given))
                .andExpect(jsonPath("$.[0].name", is(STUDENT_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).getById(given);
    }

    @Test
    void delete_Succest() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/faculties/{id}", given))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).delete(given);
    }

    @Test
    void add_Success() throws Exception {
        FacultyRequestDto given = facultyRequestDto;
        FacultyResponseDto expected = facultyResponseDto;

        when(facultyService.add(given)).thenReturn(expected);
        mockMvc.perform(post("/faculties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(given)))
                .andExpect(jsonPath("$.name", is(FACULTY_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).add(given);
    }

    @Test
    void addSubject_Success() throws Exception {
        Integer givenId = ID;
        SubjectRequestDto givenDto = subjectRequestDto;
        SubjectResponseDto expected = subjectResponseDto;

        when(facultyService.addSubjectToFaculty(givenId, givenDto)).thenReturn(expected);
        mockMvc.perform(post("/faculties/{id}/subjects", givenId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(givenDto)))
                .andExpect(jsonPath("$.name", is(SUBJECT_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).addSubjectToFaculty(givenId, givenDto);
    }

    @Test
    void addTeacher_Success() throws Exception {
        Integer givenId = ID;
        TeacherRequestDto givenDto = teacherRequestDto;
        TeacherResponseDto expected = teacherResponseDto;

        when(facultyService.addTeacherToFaculty(givenId, givenDto)).thenReturn(expected);
        mockMvc.perform(post("/faculties/{id}/teachers", givenId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(givenDto)))
                .andExpect(jsonPath("$.name", is(TEACHER_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).addTeacherToFaculty(givenId, givenDto);
    }

    @Test
    void addStudent_Success() throws Exception {
        Integer givenId = ID;
        StudentRequestDto givenDto = studentRequestDto;
        StudentResponseDto expected = studentResponseDto;

        when(facultyService.addStudentToFaculty(givenId, givenDto)).thenReturn(expected);
        mockMvc.perform(post("/faculties/{id}/students", givenId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(givenDto)))
                .andExpect(jsonPath("$.name", is(STUDENT_NAME)))
                .andExpect(status().isOk());
        verify(facultyService, times(1)).addStudentToFaculty(givenId, givenDto);
    }
}
