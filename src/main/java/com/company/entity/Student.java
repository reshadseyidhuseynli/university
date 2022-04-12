package com.company.entity;

import com.company.abstraction.Person;
import com.company.enamerations.Grade;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student extends Person implements Serializable {

    @Column(name = "grade")
    private Grade grade;

    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;

    public Student(){
    }
    public Student(int id, String name, String surname, LocalDate birthdate, Faculty faculty) {
        super(id, name, surname, birthdate);
        this.faculty = faculty;
        this.grade = Grade.BACHELOR_I;
    }

    public Grade getBachelorGrade() {
        return grade;
    }

    public void setBachelorGrade(Grade grade) {
        this.grade = grade;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
