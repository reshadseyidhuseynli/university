package com.company.service;

import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.BookMapper;
import com.company.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookResponseDto> getAll() {
        return bookMapper.toBookDtoList(bookRepository.findAll());
    }

    public BookResponseDto getById(Integer id) {
        return bookMapper.toBookDto(findByIdIfAvailable(id));
    }

    public BookResponseDto delete(Integer id) {
        final Book book = findByIdIfAvailable(id);
        bookRepository.delete(book);

        return bookMapper.toBookDto(book);
    }

    private Book findByIdIfAvailable(Integer id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.BOOK_NOT_FOUND,
                        "Book not found, by id: " + id));
    }

}
