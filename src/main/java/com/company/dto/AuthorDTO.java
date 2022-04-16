package com.company.dto;

import com.company.database.entity.Author;
import com.company.database.entity.Book;
import com.company.util.abstraction.IdentifierDTO;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO extends IdentifierDTO {

    private List<BookDTO> books;

    public AuthorDTO() {
    }

    public AuthorDTO(Author author) {
        setId(author.getId());
        setName(author.getName());

        List<BookDTO> list = new ArrayList<>();
        for (Book b : author.getBooks()){
            list.add(new BookDTO(b));
        }
        this.books = list;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
