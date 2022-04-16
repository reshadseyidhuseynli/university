package com.company.restful;

import com.company.database.entity.Book;
import com.company.database.service.inter.BookService;
import com.company.dto.BookDTO;
import com.company.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    @RequestMapping("/books")
    public ResponseEntity<ResponseDTO> getAll(){
        List<BookDTO> response = new ArrayList<>();
        for (Book b : service.getAll()){
            response.add(new BookDTO(b));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/books/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id){
        BookDTO response = new BookDTO(service.getById(id));
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @DeleteMapping
    @RequestMapping("/books/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Book book = service.getById(id);
        service.delete(book);
        BookDTO response = new BookDTO(book);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }
}
