package com.company.controller;

import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    private static final Integer ID = 1;
    private static final Integer COUNT = 1;
    private static final String QUESTION_TEXT = "testQuestion";

    private static QuestionResponseDto questionResponseDto;
    private static QuestionWithoutAnswerResponseDto questionWAResponseDto;
    private static List<Integer> idList;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    QuestionService questionService;

    @BeforeAll
    static void init() {
        questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setText(QUESTION_TEXT);
        questionWAResponseDto = new QuestionWithoutAnswerResponseDto();
        questionWAResponseDto.setText(QUESTION_TEXT);
        idList = new ArrayList<>();
        idList.add(ID);
    }

    @Test
    void getAll_Success() throws Exception {
        when(questionService.getAll())
                .thenReturn(Collections.singletonList(questionResponseDto));
        mockMvc.perform(get("/questions"))
                .andExpect(jsonPath("$.[0].text", is(QUESTION_TEXT)))
                .andExpect(status().isOk());
    }

    @Test
    void getById_Success() throws Exception {
        Integer given = ID;
        QuestionResponseDto expected = questionResponseDto;

        when(questionService.getById(given)).thenReturn(expected);
        mockMvc.perform(get("/questions/{id}", given))
                .andExpect(jsonPath("$.text", is(QUESTION_TEXT)))
                .andExpect(status().isOk());
        verify(questionService, times(1)).getById(given);
    }

    @Test
    void getRandomQuestion_Success() throws Exception {
        Integer givenSubjectId = ID;
        Integer givenCount = COUNT;

        when(questionService.getRandomQuestions(givenSubjectId, givenCount))
                .thenReturn(Collections.singletonList(questionWAResponseDto));
        mockMvc.perform(get("/questions/random/{subjectId}/{count}",
                        givenSubjectId,
                        givenCount))
                .andExpect(jsonPath("$.[0].text", is(QUESTION_TEXT)))
                .andExpect(status().isOk());
        verify(questionService, times(1))
                .getRandomQuestions(givenSubjectId, givenCount);
    }

    @Test
    void getByIdList_Success() throws Exception {
        List<Integer> given = idList;

        when(questionService.getByIdList(given))
                .thenReturn(Collections.singletonList(questionResponseDto));
        mockMvc.perform(get("/questions/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(given)))
                .andExpect(jsonPath("$.[0].text", is(QUESTION_TEXT)))
                .andExpect(status().isOk());
        verify(questionService, times(1)).getByIdList(given);
    }

    @Test
    void delete_Success() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/questions/{id}", given))
                .andExpect(status().isOk());
        verify(questionService, times(1)).delete(given);
    }
}
