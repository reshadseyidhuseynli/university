package com.company.controller;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    private static final Integer ID = 1;
    private static final String STUDENT_NAME = "testStudent";
    private static final Integer EXAM_RESULT_ID = 10;
    private static final Integer COUNT_OF_TRUE_ANSWERS = 9;

    private static StudentResponseDto studentResponseDto;
    private static ExamResultRequestDto examResultRequestDto;
    private static ExamResultResponseDto examResultResponseDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StudentService studentService;

    @BeforeAll
    static void init() {
        studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(STUDENT_NAME);
        examResultResponseDto = new ExamResultResponseDto();
        examResultResponseDto.setId(EXAM_RESULT_ID);
        examResultResponseDto.setCountOfTrueAnswers(COUNT_OF_TRUE_ANSWERS);
        studentResponseDto.setExamResults(Collections.singletonList(examResultResponseDto));
        examResultRequestDto = new ExamResultRequestDto();
        examResultRequestDto.setTrueAnswerCount(COUNT_OF_TRUE_ANSWERS);
    }

    @Test
    void getAll_Success() throws Exception {
        when(studentService.getAll())
                .thenReturn(Collections.singletonList(studentResponseDto));
        mockMvc.perform(get("/students"))
                .andExpect(jsonPath("$.[0].name", is(STUDENT_NAME)))
                .andExpect(status().isOk());
        verify(studentService, times(1)).getAll();
    }

    @Test
    void getById_Success() throws Exception {
        Integer given = ID;
        StudentResponseDto expected = studentResponseDto;

        when(studentService.getById(given)).thenReturn(expected);
        mockMvc.perform(get("/students/{id}", given))
                .andExpect(jsonPath("$.name", is(STUDENT_NAME)))
                .andExpect(status().isOk());
        verify(studentService, times(1)).getById(given);
    }

    @Test
    void getStudentResultsById_Success() throws Exception {
        Integer given = ID;

        when(studentService.getById(given)).thenReturn(studentResponseDto);
        mockMvc.perform(get("/students/{id}/results", given))
                .andExpect(jsonPath("$.[0].id", is(EXAM_RESULT_ID)))
                .andExpect(status().isOk());
        verify(studentService, times(1)).getById(given);
    }

    @Test
    void delete_Success() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/students/{id}", given))
                .andExpect(status().isOk());
        verify(studentService, times(1)).delete(given);
    }

    @Test
    void addExamResult_Success() throws Exception {
        Integer givenId = ID;
        ExamResultRequestDto givenDto = examResultRequestDto;
        ExamResultResponseDto expected = examResultResponseDto;

        when(studentService.addExamResultForStudent(givenId, givenDto)).thenReturn(expected);
        mockMvc.perform(post("/students/{id}/result", givenId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(givenDto)))
                .andExpect(jsonPath("$.countOfTrueAnswers", is(COUNT_OF_TRUE_ANSWERS)))
                .andExpect(status().isOk());
        verify(studentService, times(1)).addExamResultForStudent(givenId, givenDto);
    }
}
