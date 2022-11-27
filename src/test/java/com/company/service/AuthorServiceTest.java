package com.company.service;

import com.company.dto.request.BookRequestDto;
import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import com.company.mapper.AuthorMapper;
import com.company.mapper.BookMapper;
import com.company.dto.request.AuthorRequestDto;
import com.company.dto.response.AuthorResponseDto;
import com.company.entity.Author;
import com.company.error.ServiceException;
import com.company.repository.AuthorRepository;
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
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorMapper authorMapper;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private AuthorService authorService;

    private static Author author;
    private static Book book;

    @BeforeAll
    public static void init() {
        author = new Author();
        book = new Book();
        book.setAuthor(author);
    }

    @Test
    void getAllTest() {
        List<Author> authorList = new ArrayList<>();
        List<AuthorResponseDto> expected = new ArrayList<>();

        Mockito.when(authorRepository.findAll()).thenReturn(authorList);
        Mockito.when(authorMapper.toAuthorDtoList(authorList)).thenReturn(expected);

        List<AuthorResponseDto> actual = authorService.getAll();

        Assertions.assertEquals(expected, actual);

        Mockito.verify(authorRepository, Mockito.times(1)).findAll();
        Mockito.verify(authorMapper, Mockito.times(1)).toAuthorDtoList(authorList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;

        AuthorResponseDto expected = new AuthorResponseDto();

        Mockito.when(authorRepository.findById(given)).thenReturn(Optional.of(author));
        Mockito.when(authorMapper.toAuthorDto(author)).thenReturn(expected);

        AuthorResponseDto actual = authorService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(authorRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(authorMapper, Mockito.times(1))
                .toAuthorDto(author);
    }

    @Test
    void getByIdNotFoundAuthorTest() {
        Integer given = 100;

        Mockito.when(authorRepository.findById(given)).thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class, () -> authorService.getById(given));
        Mockito.verify(authorRepository, Mockito.times(1)).findById(given);
        Mockito.verify(authorMapper, Mockito.never()).toAuthorDto(author);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        authorService.delete(given);
        Mockito.verify(authorRepository, Mockito.times(1))
                .deleteById(given);
    }

    @Test
    void addTest() {
        AuthorRequestDto given = new AuthorRequestDto();
        AuthorResponseDto expected = new AuthorResponseDto();

        Mockito.when(authorMapper.toAuthor(given)).thenReturn(author);
        Mockito.when(authorRepository.save(author)).thenReturn(author);
        Mockito.when(authorMapper.toAuthorDto(author)).thenReturn(expected);

        AuthorResponseDto actual = authorService.add(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(authorMapper, Mockito.times(1))
                .toAuthor(given);
        Mockito.verify(authorRepository, Mockito.times(1))
                .save(author);
        Mockito.verify(authorMapper, Mockito.times(1))
                .toAuthorDto(author);
    }

    @Test
    void addBookToAuthorTest() {
        Integer givenId = 1;
        BookRequestDto givenDto = new BookRequestDto();

        BookResponseDto expected = new BookResponseDto();

        Mockito.when(authorRepository.findById(givenId)).thenReturn(Optional.of(author));
        Mockito.when(bookMapper.toBook(givenDto)).thenReturn(book);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Mockito.when(bookMapper.toBookDto(book)).thenReturn(expected);

        BookResponseDto actual = authorService.addBookToAuthor(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(authorRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(bookMapper, Mockito.times(1))
                .toBook(givenDto);
        Mockito.verify(bookRepository, Mockito.times(1))
                .save(book);
        Mockito.verify(bookMapper, Mockito.times(1))
                .toBookDto(book);
    }

    @Test
    void addBookToAuthorNotFoundAuthorTest() {
        Integer givenInt = 100;
        BookRequestDto givenDto = new BookRequestDto();

        Mockito.when(authorRepository.findById(givenInt)).thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> authorService.addBookToAuthor(givenInt, givenDto));
        Mockito.verify(authorRepository, Mockito.times(1))
                .findById(givenInt);
        Mockito.verify(bookMapper, Mockito.never())
                .toBook(givenDto);
        Mockito.verify(bookRepository, Mockito.never())
                .save(book);
        Mockito.verify(bookMapper, Mockito.never())
                .toBookDto(book);
    }
}
