package com.company.mapper;

import com.company.dto.request.AuthorRequestDto;
import com.company.dto.response.AuthorResponseDto;
import com.company.dto.response.BookResponseDto;
import com.company.entity.Author;
import com.company.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AuthorMapperTest {

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private static Author author;
    private static AuthorResponseDto authorResponseDto;

    @BeforeAll
    private static void init() {
        author = new Author();
        author.setId(1);
        author.setName("testAuthor");
        Book book = new Book();
        book.setId(1);
        author.setBooks(Collections.singletonList(book));

        authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(1);
        authorResponseDto.setName("testAuthor");
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(1);
        authorResponseDto.setBooks(Collections.singletonList(bookResponseDto));
    }

    @Test
    void toAuthorDtoTest() {
        AuthorResponseDto actual = authorMapper.toAuthorDto(author);

        Assertions.assertEquals(authorResponseDto, actual);
    }

    @Test
    void toAuthorTest() {
        AuthorRequestDto given = new AuthorRequestDto();
        given.setName("testAuthor");

        Author expected = new Author();
        expected.setName("testAuthor");

        Author actual = authorMapper.toAuthor(given);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toAuthorDtoListTest() {
        List<Author> given = new ArrayList<>();
        given.add(author);

        List<AuthorResponseDto> expected = new ArrayList<>();
        expected.add(authorResponseDto);

        List<AuthorResponseDto> actual = authorMapper.toAuthorDtoList(given);

        Assertions.assertEquals(expected, actual);
    }
}
