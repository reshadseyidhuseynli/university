package com.company.database.entity;

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
    private List<JoinTeacherLesson> teachers;

    public Lesson(){
    }
    public Lesson(int id, String name, Faculty faculty) {
        super(id, name);
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<JoinTeacherLesson> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<JoinTeacherLesson> teachers) {
        this.teachers = teachers;
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
