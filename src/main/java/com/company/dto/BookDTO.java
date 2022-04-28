package com.company.dto;

import com.company.enamerations.BookType;
import com.company.entity.Book;

public class BookDTO {

    private Integer id;
    private String name;
    private AuthorDTO author;
    private BookType type;
    private Integer page;
    private boolean hardCover;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        setId(book.getId());
        setName(book.getName());
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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public boolean isHardCover() {
        return hardCover;
    }

    public void setHardCover(boolean hardCover) {
        this.hardCover = hardCover;
    }
}
