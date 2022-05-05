package com.company.controller;

import com.company.dto.BookDto;
import com.company.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {

        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> getById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(bookService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BookDto> delete(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(bookService.delete(id));
    }
}
