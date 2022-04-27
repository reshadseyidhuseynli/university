package com.company.controller;

import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> getAllAuthors() {
        final List<AuthorDTO> all = authorService.getAll();

        return ResponseEntity.ok(ResponseDTO.create(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getAuthorById(@PathVariable("id") Integer id) {
        final AuthorDTO byId = authorService.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @GetMapping("{id}/books")
    public ResponseEntity<ResponseDTO> getAuthorBooks(@PathVariable("id") Integer id) {
        final List<BookDTO> bookDTOS = authorService.getById(id).getBooks();

        return ResponseEntity.ok(ResponseDTO.create(bookDTOS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteAuthor(@PathVariable("id") Integer id) {
        final AuthorDTO authorDTO = authorService.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(authorDTO, "The author successfully deleted"));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addAuthor(@RequestParam(value = "name") String name) {
        final AuthorDTO authorDTO = authorService.add(name);

        return ResponseEntity.ok(ResponseDTO.create(authorDTO, "The author successfully added"));
    }

    @PostMapping("{id}/add+book")
    public ResponseEntity<ResponseDTO> addBook(@PathVariable("id") Integer id,
                                               @RequestParam(value = "name") String name,
                                               @RequestParam(value = "page") Integer page) {
        final BookDTO bookDTO = authorService.addBookToAuthor(id, name, page);

        return ResponseEntity.ok(ResponseDTO.create(bookDTO, "The Book successfully added"));
    }
}
