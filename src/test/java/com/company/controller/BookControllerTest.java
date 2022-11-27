package com.company.controller;

import com.company.dto.response.BookResponseDto;
import com.company.service.BookService;
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

@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final Integer ID = 1;
    private static final String BOOK_NAME = "testBook";

    private static BookResponseDto bookResponseDto;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @BeforeAll
    static void init() {
        bookResponseDto = new BookResponseDto();
        bookResponseDto.setName(BOOK_NAME);
    }

    @Test
    void getAll_Success() throws Exception {
        when(bookService.getAll()).thenReturn(Collections.singletonList(bookResponseDto));

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$.[0].name", is(BOOK_NAME)))
                .andExpect(status().isOk());
        verify(bookService, times(1)).getAll();
    }

    @Test
    void getById_Success() throws Exception {
        Integer given = ID;
        BookResponseDto expected = bookResponseDto;

        when(bookService.getById(given)).thenReturn(expected);

        mockMvc.perform(get("/books/{id}", given))
                .andExpect(jsonPath("$.name", is(BOOK_NAME)))
                .andExpect(status().isOk());
        verify(bookService, times(1)).getById(given);
    }

    @Test
    void delete_Success() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/books/{id}", given))
                .andExpect(status().isOk());
        verify(bookService, times(1)).delete(given);
    }
}
