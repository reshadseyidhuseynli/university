package com.company.service;

import com.company.dto.AuthorDto;
import com.company.dto.BookDto;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.error.ServiceException;
import com.company.mapper.AuthorMapper;
import com.company.mapper.BookMapper;
import com.company.repository.AuthorRepository;
import com.company.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    AuthorService(AuthorRepository authorRepository,
                  BookRepository bookRepository,
                  AuthorMapper authorMapper,
                  BookMapper bookMapper) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;

    }

    public List<AuthorDto> getAll() {

        return authorMapper.toAuthorDtoList(authorRepository.findAll());
    }

    public AuthorDto getById(Integer id) {

        return authorMapper.toAuthorDto(authorRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Author not found, by id: " + id)));
    }

    public AuthorDto delete(Integer id) {

        final Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Author not found, by id: " + id));

        authorRepository.delete(author);

        return authorMapper.toAuthorDto(author);

    }

    public AuthorDto add(String name) {

        return authorMapper.toAuthorDto(authorRepository.save(new Author(name)));
    }

    public BookDto addBookToAuthor(Integer authorId, String name, Integer page) {

        final Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ServiceException("Author not found, by id: " + authorId));

        final Book book = bookRepository.save(new Book(name, author, page));

        return bookMapper.toBookDto(book);

    }

}
