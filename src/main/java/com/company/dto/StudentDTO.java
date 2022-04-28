package com.company.dto;

import com.company.enamerations.Grade;
import com.company.entity.ExamResult;
import com.company.entity.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO {

    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Grade grade;
    private FacultyDTO faculty;
    private List<ExamResultDTO> examResults;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        setId(student.getId());
        setName(student.getName());
        setSurname(student.getSurname());
        setBirthdate(student.getBirthdate());
        this.grade = student.getGrade();
        this.faculty = new FacultyDTO(student.getFaculty());
        this.examResults = new ArrayList<>();
        for (ExamResult er : student.getExamResults()) {
            examResults.add(new ExamResultDTO(er));
        }
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

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public List<ExamResultDTO> getExamResults() {
        return examResults;
    }

    public void setExamResults(List<ExamResultDTO> examResults) {
        this.examResults = examResults;
    }
}
