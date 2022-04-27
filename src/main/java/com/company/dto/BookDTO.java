package com.company.dto;

import com.company.entity.Book;
import com.company.util.abstraction.IdentifierDTO;
import com.company.util.enamerations.BookType;

public class BookDTO extends IdentifierDTO {

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
