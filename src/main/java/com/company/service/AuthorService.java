package com.company.service;

import com.company.dto.response.AuthorResponseDto;
import com.company.dto.response.BookResponseDto;
import com.company.dto.request.AuthorRequestDto;
import com.company.dto.request.BookRequestDto;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.AuthorMapper;
import com.company.mapper.BookMapper;
import com.company.repository.AuthorRepository;
import com.company.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public List<AuthorResponseDto> getAll() {
        return authorMapper.toAuthorDtoList(authorRepository.findAll());
    }

    public AuthorResponseDto getById(Integer id) {
        return authorMapper.toAuthorDto(findByIdIfAvailable(id));
    }

    public AuthorResponseDto delete(Integer id) {
        final Author author = findByIdIfAvailable(id);
        authorRepository.delete(author);

        return authorMapper.toAuthorDto(author);
    }

    public AuthorResponseDto add(AuthorRequestDto requestDto) {
        return authorMapper.toAuthorDto(
                authorRepository.save(authorMapper.toAuthor(requestDto)));
    }

    public BookResponseDto addBookToAuthor(Integer authorId, BookRequestDto requestDto) {
        final Author author = findByIdIfAvailable(authorId);
        final Book book = bookMapper.toBook(requestDto);
        book.setAuthor(author);

        return bookMapper.toBookDto(bookRepository.save(book));
    }

    private Author findByIdIfAvailable(Integer id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.AUTHOR_NOT_FOUND,
                        "Author not found, by id: " + id));
    }

}
