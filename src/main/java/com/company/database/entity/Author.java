package com.company.database.entity;

import com.company.util.abstraction.IdentifierEntity;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends IdentifierEntity implements Serializable {

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String name) {
        super(name);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Entity.Author{" + "id:" + getId() + ", " + getName() + '}';
    }
}
