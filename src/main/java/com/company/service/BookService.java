package com.company.service;

import com.company.dto.response.BookResponseDto;
import com.company.entity.Book;
import com.company.mapper.BookMapper;
import com.company.repository.BookRepository;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
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
        return bookMapper.toBookDto(findById(id));
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    private Book findById(Integer id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.BOOK_NOT_FOUND,
                        "Book not found, by id: " + id));
    }

}
