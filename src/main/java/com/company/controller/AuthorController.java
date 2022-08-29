package com.company.controller;

import com.company.dto.response.AuthorResponseDto;
import com.company.dto.response.BookResponseDto;
import com.company.dto.request.AuthorRequestDto;
import com.company.dto.request.BookRequestDto;
import com.company.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Integer id) {
        return ResponseEntity.ok(authorService.getById(id));
    }

    @GetMapping("{id}/books")
    public ResponseEntity<List<BookResponseDto>> getAuthorBooks(@PathVariable Integer id) {
        return ResponseEntity.ok(authorService.getById(id).getBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> deleteAuthor(@PathVariable Integer id) {
        return ResponseEntity.ok(authorService.delete(id));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody AuthorRequestDto requestDto) {
        return ResponseEntity.ok(authorService.add(requestDto));
    }

    @PostMapping("{id}/books")
    public ResponseEntity<BookResponseDto> addBook(@PathVariable Integer id,
                                                   @RequestBody BookRequestDto requestDto) {
        return ResponseEntity.ok(authorService.addBookToAuthor(id, requestDto));
    }
}
