package com.company.controller;

import com.company.dto.response.StudentResponseDto;
import com.company.service.TeacherService;
import com.company.dto.response.TeacherResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    private static final Integer ID = 1;
    private static final String TEACHER_NAME = "testTeacher";
    private static final String STUDENT_NAME = "testStudent";

    private static TeacherResponseDto teacherResponseDto;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TeacherService teacherService;

    @BeforeAll
    static void init() {
        teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setName(TEACHER_NAME);
    }

    @Test
    void getAll_Success() throws Exception {
        when(teacherService.getAll())
                .thenReturn(Collections.singletonList(teacherResponseDto));
        mockMvc.perform(get("/teachers"))
                .andExpect(jsonPath("$.[0].name", is(TEACHER_NAME)))
                .andExpect(status().isOk());
        verify(teacherService, times(1)).getAll();
    }

    @Test
    void getById_Success() throws Exception {
        Integer given = ID;
        TeacherResponseDto expected = teacherResponseDto;

        when(teacherService.getById(given)).thenReturn(expected);
        mockMvc.perform(get("/teachers/{id}", given))
                .andExpect(jsonPath("$.name", is(TEACHER_NAME)))
                .andExpect(status().isOk());
        verify(teacherService, times(1)).getById(given);
    }

    @Test
    void getTeacherStudents_Success() throws Exception {
        Integer given = ID;
        StudentResponseDto student = new StudentResponseDto();
        student.setName(STUDENT_NAME);

        when(teacherService.getStudentsOfTeacherById(given)).thenReturn(Collections.singletonList(student));
        mockMvc.perform(get("/teachers/{id}/students", given))
                .andExpect(jsonPath("$.[0].name", is(STUDENT_NAME)))
                .andExpect(status().isOk());
        verify(teacherService, times(1)).getStudentsOfTeacherById(given);
    }

    @Test
    void delete_Success() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/teachers/{id}", given))
                .andExpect(status().isOk());
        verify(teacherService, times(1)).delete(given);
    }
}
