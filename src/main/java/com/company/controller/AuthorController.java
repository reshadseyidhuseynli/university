package com.company.controller;

import com.company.dto.request.AuthorRequestDto;
import com.company.dto.request.BookRequestDto;
import com.company.dto.response.AuthorResponseDto;
import com.company.dto.response.BookResponseDto;
import com.company.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorResponseDto> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorResponseDto getById(@PathVariable Integer id) {
        return authorService.getById(id);
    }

    @GetMapping("{id}/books")
    public List<BookResponseDto> getAuthorBooksById(@PathVariable Integer id) {
        return authorService.getById(id).getBooks();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        authorService.delete(id);
    }

    @PostMapping
    public AuthorResponseDto add(@Valid @RequestBody AuthorRequestDto requestDto) {
        return authorService.add(requestDto);
    }

    @PostMapping("{id}/books")
    public BookResponseDto addBook(@PathVariable Integer id,
                                   @RequestBody BookRequestDto requestDto) {
        return authorService.addBookToAuthor(id, requestDto);
    }
}
