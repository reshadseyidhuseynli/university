package com.company.controller;

import com.company.dto.request.AuthorRequestDto;
import com.company.dto.request.BookRequestDto;
import com.company.dto.response.AuthorResponseDto;
import com.company.dto.response.BookResponseDto;
import com.company.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

@WebMvcTest(value = AuthorController.class)
class AuthorControllerTest {

    private static final String AUTHOR_NAME = "testAuthor";
    private static final String BOOK_NAME = "testBook";
    private static final Integer ID = 1;

    private static AuthorResponseDto authorResponseDto;
    private static BookResponseDto bookResponseDto;
    private static AuthorRequestDto authorRequestDto;
    private static BookRequestDto bookRequestDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @BeforeAll
    static void init() {
        authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(AUTHOR_NAME);
        bookResponseDto = new BookResponseDto();
        bookResponseDto.setName(BOOK_NAME);
        authorResponseDto.setBooks(Collections.singletonList(bookResponseDto));

        authorRequestDto = new AuthorRequestDto();
        authorRequestDto.setName(AUTHOR_NAME);

        bookRequestDto = new BookRequestDto();
        bookRequestDto.setName(BOOK_NAME);
    }

    @Test
    void getAllAuthors_Success() throws Exception {
        when(authorService.getAll()).thenReturn(Collections.singletonList(authorResponseDto));

        mockMvc.perform(get("/authors"))
                .andExpect(jsonPath("$.[0].name", is(AUTHOR_NAME)))
                .andExpect(status().isOk());
        verify(authorService, times(1)).getAll();
    }

    @Test
    void getAuthorById_Success() throws Exception {
        Integer given = ID;
        AuthorResponseDto expected = authorResponseDto;

        Mockito.when(authorService.getById(given)).thenReturn(expected);

        mockMvc.perform(get("/authors/{id}", given))
                .andExpect(jsonPath("$.name", is(AUTHOR_NAME)))
                .andExpect(status().isOk());
        verify(authorService, times(1)).getById(given);
    }

    @Test
    void getAuthorBooksById_Success() throws Exception {
        Integer given = ID;

        Mockito.when(authorService.getById(given)).thenReturn(authorResponseDto);

        mockMvc.perform(get("/authors/{id}/books", given))
                .andExpect(jsonPath("$.[0].name", is(BOOK_NAME)))
                .andExpect(status().isOk());
        verify(authorService, times(1)).getById(given);
    }

    @Test
    void deleteAuthor_Success() throws Exception {
        Integer given = ID;
        mockMvc.perform(delete("/authors/{id}", given))
                .andExpect(status().isOk());
        verify(authorService, times(1)).delete(given);
    }

    @Test
    void addAuthor_Success() throws Exception {
        AuthorRequestDto given = authorRequestDto;
        AuthorResponseDto expected = authorResponseDto;

        when(authorService.add(given)).thenReturn(expected);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(given)))
                .andExpect(jsonPath("$.name", is(AUTHOR_NAME)))
                .andExpect(status().isOk());
        verify(authorService, times(1)).add(given);
    }

    @Test
    void addBook_Success() throws Exception {
        Integer givenId = ID;
        BookRequestDto givenDto = bookRequestDto;
        BookResponseDto expected = bookResponseDto;

        when(authorService.addBookToAuthor(givenId, givenDto)).thenReturn(expected);

        mockMvc.perform(post("/authors/{id}/books", givenId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(givenDto)))
                .andExpect(jsonPath("$.name", is(BOOK_NAME)))
                .andExpect(status().isOk());
        verify(authorService, times(1)).addBookToAuthor(givenId, givenDto);
    }

}
