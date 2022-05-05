package com.company.dto;

import com.company.model.Grade;

import java.time.LocalDate;
import java.util.List;

public class StudentDto {

    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Grade grade;
    private FacultyDto faculty;
    private List<ExamResultDto> examResults;

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

    public FacultyDto getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDto faculty) {
        this.faculty = faculty;
    }

    public List<ExamResultDto> getExamResults() {
        return examResults;
    }

    public void setExamResults(List<ExamResultDto> examResults) {
        this.examResults = examResults;
    }

}
