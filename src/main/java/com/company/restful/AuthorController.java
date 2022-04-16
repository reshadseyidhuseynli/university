package com.company.restful;

import com.company.database.entity.Author;
import com.company.database.entity.Book;
import com.company.database.service.inter.AuthorService;
import com.company.dto.AuthorDTO;
import com.company.dto.BookDTO;
import com.company.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping
    @RequestMapping("/authors")
    public ResponseEntity<ResponseDTO> getAll(){
        List<AuthorDTO> response = new ArrayList<>();
        for (Author a : service.getAll()){
            response.add(new AuthorDTO(a));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/authors/{id}/books")
    public ResponseEntity<ResponseDTO> getTheAuthorBooks(@PathVariable("id") Integer id){
        List<BookDTO> response = new AuthorDTO(service.getById(id)).getBooks();
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/authors/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id){
        AuthorDTO response = new AuthorDTO(service.getById(id));
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @DeleteMapping
    @RequestMapping("/authors/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Author author = service.getById(id);
        service.delete(author);
        AuthorDTO response = new AuthorDTO(author);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }

    @GetMapping
    @RequestMapping("/authors")
    public ResponseEntity<ResponseDTO> add (@RequestParam(value = "name") String name){
        Author author = new Author(name);
        service.addOrUpdate(author);
        AuthorDTO response = new AuthorDTO(author);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully added"));
    }

    @GetMapping
    @RequestMapping("/authors/{id}/add+book")
    public ResponseEntity<ResponseDTO> addBook(@PathVariable("id") Integer id,
                                                        @RequestParam(value = "name") String name,
                                                        @RequestParam(value = "page") Integer page){
        Author author = service.getById(id);
        Book newBook = new Book(name, author ,page);
        author.getBooks().add(newBook);
        BookDTO response = new BookDTO(newBook);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully added"));
    }
}
