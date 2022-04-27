package com.company.service;

import com.company.dto.BookDTO;
import com.company.entity.Book;
import com.company.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookService{

    private final BookRepository bookRepository;

    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAll() {
        final List<Book> all = bookRepository.findAll();

        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book : all) {
            bookDTOS.add(new BookDTO(book));
        }

        return bookDTOS;
    }

    public BookDTO getById(Integer id) {
        final Book byId = bookRepository.getById(id);

        return new BookDTO(byId);
    }

    public BookDTO delete(Integer id) {
        final Book byId = bookRepository.getById(id);
        bookRepository.delete(byId);

        return new BookDTO(byId);
    }
}
