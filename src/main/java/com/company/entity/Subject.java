package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "subject")
    @ToString.Exclude
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "subject")
    @ToString.Exclude
    private List<Question> questions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Subject subject = (Subject) o;
        return id != null && Objects.equals(id, subject.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
