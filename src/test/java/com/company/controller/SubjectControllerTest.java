package com.company.controller;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.service.SubjectService;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
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

@WebMvcTest(SubjectController.class)
class SubjectControllerTest {

    private static final Integer ID = 1;
    private static final Integer QUESTION_ID = 10;
    private static final String SUBJECT_NAME = "testSubject";

    private static SubjectResponseDto subjectResponseDto;
    private static QuestionRequestDto questionRequestDto;
    private static QuestionResponseDto questionResponseDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SubjectService subjectService;

    @BeforeAll
    static void init() {
        subjectResponseDto = new SubjectResponseDto();
        subjectResponseDto.setName(SUBJECT_NAME);
        QuestionWithoutAnswerResponseDto questionWAResponseDto = new QuestionWithoutAnswerResponseDto();
        questionWAResponseDto.setId(QUESTION_ID);
        subjectResponseDto
                .setQuestions(Collections.singletonList(questionWAResponseDto));
        questionRequestDto = new QuestionRequestDto();
        questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setId(QUESTION_ID);
    }

    @Test
    void getAll_Success() throws Exception {
        when(subjectService.getAll()).thenReturn(Collections.singletonList(subjectResponseDto));
        mockMvc.perform(get("/subjects"))
                .andExpect(jsonPath("$.[0].name", is(SUBJECT_NAME)))
                .andExpect(status().isOk());
        verify(subjectService, times(1)).getAll();
    }

    @Test
    void getById_Success() throws Exception {
        Integer given = ID;
        SubjectResponseDto expected = subjectResponseDto;

        when(subjectService.getById(given)).thenReturn(expected);
        mockMvc.perform(get("/subjects/{id}", given))
                .andExpect(jsonPath("$.name", is(SUBJECT_NAME)))
                .andExpect(status().isOk());
        verify(subjectService, times(1)).getById(given);
    }

    @Test
    void getSubjectQuestionsById_Success() throws Exception {
        Integer given = ID;

        when(subjectService.getById(given)).thenReturn(subjectResponseDto);
        mockMvc.perform(get("/subjects/{id}/questions", given))
                .andExpect(jsonPath("$.[0].id", is(QUESTION_ID)))
                .andExpect(status().isOk());
        verify(subjectService, times(1)).getById(given);
    }

    @Test
    void delete_Success() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/subjects/{id}", given))
                .andExpect(status().isOk());
        verify(subjectService, times(1)).delete(given);
    }

    @Test
    void addQuestion_Success() throws Exception {
        Integer givenId = ID;
        QuestionRequestDto givenDto = questionRequestDto;
        QuestionResponseDto expected = questionResponseDto;

        when(subjectService.addQuestionToLesson(givenId, givenDto))
                .thenReturn(expected);
        mockMvc.perform(post("/subjects/{id}/question", givenId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(givenDto)))
                .andExpect(jsonPath("$.id", is(QUESTION_ID)))
                .andExpect(status().isOk());
        verify(subjectService, times(1))
                .addQuestionToLesson(givenId, givenDto);
    }
}
