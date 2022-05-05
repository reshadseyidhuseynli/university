package com.company.entity;

import com.company.model.Grade;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
public class Student implements Serializable {

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
    @Column(name = "grade")
    private Grade grade;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;
    @OneToMany(mappedBy = "student")
    private List<ExamResult> examResults;

    public Student(){
    }
    public Student(Faculty faculty, String name, String surname, LocalDate birthdate) {

        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.faculty = faculty;
        this.grade = Grade.BACHELOR_I;
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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<ExamResult> getExamResults() {
        return examResults;
    }

    public void setExamResults(List<ExamResult> examResults) {
        this.examResults = examResults;
    }
}
