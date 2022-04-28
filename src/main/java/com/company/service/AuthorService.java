package com.company.service;

import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.error.ServiceException;
import com.company.repository.AuthorRepository;
import com.company.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<AuthorDTO> getAll() {
        final List<Author> all = authorRepository.findAll();

        List<AuthorDTO> authorDTOList = new ArrayList<>();
        for (Author author : all) {
            authorDTOList.add(new AuthorDTO(author));
        }

        return authorDTOList;
    }

    public AuthorDTO getById(Integer id) {
        final Author byId = authorRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Author not found, by id: " + id));

        return new AuthorDTO(byId);
    }

    public AuthorDTO delete(Integer id) {
        final Author byId = authorRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Author not found, by id: " + id));
        authorRepository.delete(byId);

        return new AuthorDTO(byId);
    }

    public AuthorDTO add(String name) {
        Author author = new Author(name);
        AuthorDTO authorDTO = new AuthorDTO(author);

        authorRepository.save(author);

        return authorDTO;
    }

    public BookDTO addBookToAuthor(Integer authorId, String name, Integer page) {
        final Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ServiceException("Author not found, by id: " + authorId));
        final Book book = bookRepository.save(new Book(name, author, page));

        return new BookDTO(book);
    }
}
