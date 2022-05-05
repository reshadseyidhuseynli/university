package com.company.entity;

import com.company.model.AcademicRank;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "academic_rank")
    private AcademicRank academicRank;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne
    private Lesson lesson;
    @OneToMany(mappedBy = "teacher")
    private List<JoinTeacherStudent> students;

    public Teacher(){
    }

    public Teacher(Faculty faculty, String name, String surname, LocalDate birthdate) {

        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.faculty = faculty;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public AcademicRank getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(AcademicRank academicRank) {
        this.academicRank = academicRank;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<JoinTeacherStudent> getStudents() {
        return students;
    }

    public void setStudents(List<JoinTeacherStudent> students) {
        this.students = students;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lessons) {
        this.lesson = lessons;
    }

    @Override
    public String toString() {
        return "Entity.Teacher{" +
                "id:" + getId() +
                ", " + getName() + " " + getSurname() +
                ", " + academicRank +
                ", department:" + faculty.getName() +
                '}';
    }
}
