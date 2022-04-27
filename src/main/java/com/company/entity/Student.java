package com.company.entity;

import com.company.util.abstraction.PersonEntity;
import com.company.util.enamerations.Grade;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends PersonEntity implements Serializable {

    @Column(name = "grade")
    private Grade grade;

    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "student")
    List<ExamResult> examResults;

    public Student(){
    }
    public Student(Faculty faculty, String name, String surname, LocalDate birthdate) {
        super(name, surname, birthdate);
        this.faculty = faculty;
        this.grade = Grade.BACHELOR_I;
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
