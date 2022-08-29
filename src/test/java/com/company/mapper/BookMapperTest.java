package com.company.mapper;

import com.company.domain.BookType;
import com.company.dto.request.BookRequestDto;
import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BookMapperTest {

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private static Book book;
    private static BookResponseDto bookResponseDto;

    @BeforeAll
    private static void init() {
        book = new Book();
        book.setId(1);
        book.setType(BookType.DRAMA);
        book.setName("test");
        book.setPage(1);
        book.setHardCover(true);

        bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(1);
        bookResponseDto.setType(BookType.DRAMA);
        bookResponseDto.setName("test");
        bookResponseDto.setPage(1);
        bookResponseDto.setHardCover(true);
    }

    @Test
    void toBookDtoTest() {
        BookResponseDto actual = bookMapper.toBookDto(book);

        Assertions.assertEquals(bookResponseDto, actual);
    }

    @Test
    void toBookTest() {
        BookRequestDto given = new BookRequestDto();
        given.setName("test");
        given.setPage(1);

        Book expected = new Book();
        expected.setName("test");
        expected.setPage(1);

        Book actual = bookMapper.toBook(given);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toBookDtoListTest() {
        List<Book> given = new ArrayList<>();
        given.add(book);

        List<BookResponseDto> expected = new ArrayList<>();
        expected.add(bookResponseDto);

        List<BookResponseDto> actual = bookMapper.toBookDtoList(given);

        Assertions.assertEquals(expected, actual);
    }

}
