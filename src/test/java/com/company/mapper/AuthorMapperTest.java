package com.company.mapper;

import com.company.dto.request.AuthorRequestDto;
import com.company.dto.response.AuthorResponseDto;
import com.company.dto.response.BookResponseDto;
import com.company.entity.Author;
import com.company.entity.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorMapperTest {

    private static final Integer ID = 1;
    private static final String AUTHOR_NAME = "testAuthor";

    private static Author author;
    private static AuthorResponseDto authorResponseDto;
    private static AuthorRequestDto authorRequestDto;
    private static Author newAuthor;

    private final AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    @BeforeAll
    public static void init() {
        author = new Author();
        author.setId(ID);
        author.setName(AUTHOR_NAME);
        Book book = new Book();
        book.setId(ID);
        author.setBooks(Collections.singletonList(book));

        authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(ID);
        authorResponseDto.setName(AUTHOR_NAME);
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(ID);
        authorResponseDto.setBooks(Collections.singletonList(bookResponseDto));

        authorRequestDto = new AuthorRequestDto();
        authorRequestDto.setName(AUTHOR_NAME);

        newAuthor = new Author();
        newAuthor.setName(AUTHOR_NAME);
    }

    @Test
    void toAuthorDtoTest() {
        Author given = author;
        AuthorResponseDto expected = authorResponseDto;
        AuthorResponseDto actual = authorMapper.toAuthorDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toAuthorTest() {
        AuthorRequestDto given = authorRequestDto;
        Author expected = newAuthor;
        Author actual = authorMapper.toAuthor(given);
        assertEquals(expected, actual);
    }

    @Test
    void toAuthorDtoListTest() {
        List<Author> given = Collections.singletonList(author);
        List<AuthorResponseDto> expected = Collections.singletonList(authorResponseDto);
        List<AuthorResponseDto> actual = authorMapper.toAuthorDtoList(given);
        assertEquals(expected, actual);
    }
}
