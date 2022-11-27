package com.company.controller;

import com.company.service.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GradeController.class)
class GradeControllerTest {

    private static final Integer ID = 1;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GradeService gradeService;

    @Test
    void passNextYearAll_Success() throws Exception {
        mockMvc.perform(put("/students/grade"))
                .andExpect(status().isOk());
        verify(gradeService, times(1)).passNextYear();
    }

    @Test
    void passNextYearById_Success() throws Exception {
        Integer givenId = ID;
        Integer givenYear = null;
        mockMvc.perform(put("/students/{id}/grade", givenId))
                .andExpect(status().isOk());
        verify(gradeService, times(1)).passNextYearByStudentId(givenId, givenYear);
    }
}
