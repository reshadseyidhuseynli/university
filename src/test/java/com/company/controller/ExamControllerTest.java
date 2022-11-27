package com.company.controller;

import com.company.dto.request.ExamAnswerRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.service.ExamService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExamController.class)
class ExamControllerTest {

    private static final Integer ID = 1;
    private static final String QUESTION_TEXT = "testText";
    private static final Integer COUNT_OF_TRUE_ANSWERS = 10;

    private static QuestionWithoutAnswerResponseDto questionWithoutAnswerResponseDto;
    private static ExamAnswerRequestDto examAnswerRequestDto;
    private static ExamResultResponseDto examResultResponseDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ExamService examService;

    @BeforeAll
    static void init() {
        questionWithoutAnswerResponseDto = new QuestionWithoutAnswerResponseDto();
        questionWithoutAnswerResponseDto.setText(QUESTION_TEXT);

        examAnswerRequestDto = new ExamAnswerRequestDto();

        examResultResponseDto = new ExamResultResponseDto();
        examResultResponseDto.setCountOfTrueAnswers(COUNT_OF_TRUE_ANSWERS);
    }

    @Test
    void getExaminationPaperByTeacherId_Success() throws Exception {
        Integer given = ID;

        when(examService.getExaminationPaperBySubjectId(given))
                .thenReturn(Collections.singletonList(questionWithoutAnswerResponseDto));

        mockMvc.perform(get("/exam?subject-id=" + given))
                .andExpect(jsonPath("$.[0].text", is(QUESTION_TEXT)))
                .andExpect(status().isOk());
        verify(examService, times(1))
                .getExaminationPaperBySubjectId(given);
    }

    @Test
    void acceptAnswersAndGiveResult_Success() throws Exception {
        ExamAnswerRequestDto given = examAnswerRequestDto;
        ExamResultResponseDto expected = examResultResponseDto;

        when(examService.acceptAnswersAndGiveResult(given))
                .thenReturn(expected);

        mockMvc.perform(post("/exam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(given)))
                .andExpect(jsonPath("$.countOfTrueAnswers", is(COUNT_OF_TRUE_ANSWERS)))
                .andExpect(status().isOk());
        verify(examService, times(1))
                .acceptAnswersAndGiveResult(given);
    }
}