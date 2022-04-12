package com.company.entity;

import com.company.abstraction.Identifier;
import com.company.enamerations.BookType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book extends Identifier implements Serializable {
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Author author;
    @Column(name= "book_type_id")
    private BookType type;
    @Column(name = "page")
    private int page;
    @Column(name = "hard_cover")
    private boolean hardCover;

    public Book(){
    }

    public Book(int id, String name, Author author, BookType type, int page, boolean hardCover) {
        super(id, name);
        this.author = author;
        this.type = type;
        this.page = page;
        this.hardCover = hardCover;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public boolean isHardCover() {
        return hardCover;
    }

    public void setHardCover(boolean hardCover) {
        this.hardCover = hardCover;
    }

    @Override
    public String toString() {
        return "Entity.Book{" +
                "id:" + getId() +
                ", " + getName() +
                ", author:" + author.getName() +
                ", page:" + page +
                '}';
    }
}
