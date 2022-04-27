package com.company.dto;

import com.company.entity.ExamResult;
import com.company.entity.Student;
import com.company.util.abstraction.PersonDTO;
import com.company.util.enamerations.Grade;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO extends PersonDTO {

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
        for (ExamResult er : student.getExamResults()){
            examResults.add(new ExamResultDTO(er));
        }
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
