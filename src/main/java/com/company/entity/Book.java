package com.company.entity;

import com.company.enamerations.BookType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Author author;
    @Column(name= "book_type_id")
    private BookType type;
    @Column(name = "page")
    private Integer page;
    @Column(name = "hard_cover")
    private boolean hardCover;

    public Book(){
    }

    public Book(String name, Author author, Integer page) {
        this.name = name;
        this.author = author;
        this.page = page;
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
