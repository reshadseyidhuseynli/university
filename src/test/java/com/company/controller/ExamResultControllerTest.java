package com.company.controller;

import com.company.dto.response.ExamResultResponseDto;
import com.company.service.ExamResultService;
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

@WebMvcTest(ExamResultController.class)
class ExamResultControllerTest {

    private static final Integer ID = 1;

    private static ExamResultResponseDto examResultResponseDto;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ExamResultService examResultService;

    @BeforeAll
    static void init() {
        examResultResponseDto = new ExamResultResponseDto();
        examResultResponseDto.setId(ID);
    }

    @Test
    void getAll_Success() throws Exception {
        when(examResultService.getAll())
                .thenReturn(Collections.singletonList(examResultResponseDto));

        mockMvc.perform(get("/results"))
                .andExpect(jsonPath("$.[0].id", is(ID)))
                .andExpect(status().isOk());
        verify(examResultService, times(1)).getAll();
    }

    @Test
    void getById_Success() throws Exception {
        Integer given = ID;
        ExamResultResponseDto expected = examResultResponseDto;

        when(examResultService.getById(given)).thenReturn(expected);

        mockMvc.perform(get("/results/{id}", given))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(status().isOk());
        verify(examResultService, times(1)).getById(given);
    }

    @Test
    void delete_Success() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/results/{id}", given))
                .andExpect(status().isOk());
        verify(examResultService, times(1)).delete(given);
    }
}