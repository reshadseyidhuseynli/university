package com.company.entity;

import com.company.domain.BookType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != null && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
