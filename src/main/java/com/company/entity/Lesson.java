package com.company.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;
    @OneToMany(mappedBy = "lesson")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "lesson")
    private List<Question> questions;

    public Lesson(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
    }

}
