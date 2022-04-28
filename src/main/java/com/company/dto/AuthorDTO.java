package com.company.dto;

import com.company.entity.Author;
import com.company.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    private Integer id;
    private String name;
    private List<BookDTO> books;

    public AuthorDTO() {
    }

    public AuthorDTO(Author author) {
        setId(author.getId());
        setName(author.getName());

        List<BookDTO> list = new ArrayList<>();
        for (Book b : author.getBooks()) {
            list.add(new BookDTO(b));
        }
        this.books = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
