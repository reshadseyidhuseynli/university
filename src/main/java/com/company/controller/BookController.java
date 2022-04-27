package com.company.controller;

import com.company.dto.BookDTO;
import com.company.dto.ResponseDTO;
import com.company.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService service;

    BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        List<BookDTO> bookDTOS = service.getAll();

        return ResponseEntity.ok(ResponseDTO.create(bookDTOS));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        BookDTO byId = service.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        BookDTO bookDTO = service.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(bookDTO, "The Book successfully deleted"));
    }
}
