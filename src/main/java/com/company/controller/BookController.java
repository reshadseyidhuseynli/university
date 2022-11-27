package com.company.controller;

import com.company.dto.response.BookResponseDto;
import com.company.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponseDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("{id}")
    public BookResponseDto getById(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        bookService.delete(id);
    }
}
