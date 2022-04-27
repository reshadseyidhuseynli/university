package com.company.entity;

import com.company.util.abstraction.IdentifierEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "lesson")
public class Lesson extends IdentifierEntity implements Serializable {

    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;
    @OneToMany(mappedBy = "lesson")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "lesson")
    private List<Question> questions;

    public Lesson(){
    }
    public Lesson(String name, Faculty faculty) {
        super(name);
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Entity.Lesson{" +
                "id:" + getId() +
                ", " + getName() +
                ", faculty:" + faculty.getName() +
                '}';
    }
}
