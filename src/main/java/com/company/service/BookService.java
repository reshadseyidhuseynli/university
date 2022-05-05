package com.company.service;

import com.company.dto.BookDto;
import com.company.entity.Book;
import com.company.error.ServiceException;
import com.company.mapper.BookMapper;
import com.company.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    BookService(BookRepository bookRepository,
                BookMapper bookMapper) {

        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;

    }

    public List<BookDto> getAll() {

        return bookMapper.toBookDtoList(bookRepository.findAll());
    }

    public BookDto getById(Integer id) {

        return bookMapper.toBookDto(bookRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Book not found, by id: " + id)));
    }

    public BookDto delete(Integer id) {

        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Book not found, by id: " + id));

        bookRepository.delete(book);

        return bookMapper.toBookDto(book);

    }

}
