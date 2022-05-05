package com.company.controller;

import com.company.dto.AuthorDto;
import com.company.dto.BookDto;
import com.company.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {

        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(authorService.getById(id));
    }

    @GetMapping("{id}/books")
    public ResponseEntity<List<BookDto>> getAuthorBooks(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(authorService.getById(id).getBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(authorService.delete(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestParam(value = "name") String name) {

        return ResponseEntity.ok(authorService.add(name));
    }

    @PostMapping("{id}/add+book")
    public ResponseEntity<BookDto> addBook(@PathVariable("id") Integer id,
                                               @RequestParam(value = "name") String name,
                                               @RequestParam(value = "page") Integer page) {

        return ResponseEntity.ok(authorService.addBookToAuthor(id, name, page));
    }
}
