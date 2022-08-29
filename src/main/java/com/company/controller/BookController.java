package com.company.controller;

import com.company.dto.response.BookResponseDto;
import com.company.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<BookResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BookResponseDto> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.delete(id));
    }
}
