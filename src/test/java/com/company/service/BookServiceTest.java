package com.company.service;

import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import com.company.error.ServiceException;
import com.company.mapper.BookMapper;
import com.company.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookService bookService;

    private static Book book;

    @BeforeAll
    private static void init() {
        book = new Book();
    }

    @Test
    void getAllTest() {
        List<Book> bookList = new ArrayList<>();
        List<BookResponseDto> expected = new ArrayList<>();

        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        Mockito.when(bookMapper.toBookDtoList(bookList)).thenReturn(expected);

        List<BookResponseDto> actual = bookService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(bookRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(bookMapper, Mockito.times(1))
                .toBookDtoList(bookList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;

        BookResponseDto expected = new BookResponseDto();

        Mockito.when(bookRepository.findById(given)).thenReturn(Optional.of(book));
        Mockito.when(bookMapper.toBookDto(book)).thenReturn(expected);

        BookResponseDto actual = bookService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(bookRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(bookMapper, Mockito.times(1))
                .toBookDto(book);
    }

    @Test
    void getByIdNotFoundBookTest() {
        Integer given = 100;

        Mockito.when(bookRepository.findById(given)).thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class, () -> bookService.getById(given));
        Mockito.verify(bookRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(bookMapper, Mockito.never())
                .toBookDto(book);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        bookService.delete(given);
        Mockito.verify(bookRepository, Mockito.times(1))
                .deleteById(given);
    }
}
