package com.company.entity;

import com.company.domain.BookType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @ManyToOne
    private Author author;

    @Column(name = "book_type")
    @Enumerated(EnumType.STRING)
    private BookType type;
    @Column(name = "page")
    private Integer page;
    @Column(name = "hard_cover")
    private boolean hardCover;

    public Book(String name, Author author, Integer page) {
        this.name = name;
        this.author = author;
        this.page = page;
    }
}
