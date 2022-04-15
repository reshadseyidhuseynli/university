package com.company.util.abstraction;

import com.company.database.entity.Faculty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Objects;

public abstract class IdentifierEntity {
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    public IdentifierEntity() {
    }

    protected IdentifierEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(getId(), faculty.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
