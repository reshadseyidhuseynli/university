package com.company.database.entity;

import com.company.util.abstraction.IdentifierEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "author")
public class Author extends IdentifierEntity implements Serializable {

    public Author() {
    }

    public Author(int id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Entity.Author{" + "id:" + getId() + ", " + getName() + '}';
    }
}