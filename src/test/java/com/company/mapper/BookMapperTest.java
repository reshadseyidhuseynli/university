package com.company.mapper;

import com.company.domain.BookType;
import com.company.dto.request.BookRequestDto;
import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {

    private static final Integer ID = 1;
    private static final Integer PAGE = 100;
    private static final String BOOK_NAME = "testBook";

    private static Book book;
    private static BookResponseDto bookResponseDto;
    private static BookRequestDto bookRequestDto;
    private static Book newBook;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @BeforeAll
    public static void init() {
        book = new Book();
        book.setId(ID);
        book.setType(BookType.DRAMA);
        book.setName(BOOK_NAME);
        book.setPage(PAGE);
        book.setHardCover(true);

        bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(ID);
        bookResponseDto.setType(BookType.DRAMA);
        bookResponseDto.setName(BOOK_NAME);
        bookResponseDto.setPage(PAGE);
        bookResponseDto.setHardCover(true);

        bookRequestDto = new BookRequestDto();
        bookRequestDto.setName(BOOK_NAME);
        bookRequestDto.setPage(PAGE);

        newBook = new Book();
        newBook.setName(BOOK_NAME);
        newBook.setPage(PAGE);
    }

    @Test
    void toBookDtoTest() {
        Book given = book;
        BookResponseDto expected = bookResponseDto;
        BookResponseDto actual = bookMapper.toBookDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toBookTest() {
        BookRequestDto given = bookRequestDto;
        Book expected = newBook;

        Book actual = bookMapper.toBook(given);
        assertEquals(expected, actual);
    }

    @Test
    void toBookDtoListTest() {
        List<Book> given = Collections.singletonList(book);
        List<BookResponseDto> expected = Collections.singletonList(bookResponseDto);
        List<BookResponseDto> actual = bookMapper.toBookDtoList(given);
        assertEquals(expected, actual);
    }

}
