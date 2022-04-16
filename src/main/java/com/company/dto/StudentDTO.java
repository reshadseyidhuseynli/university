package com.company.dto;

import com.company.database.entity.Student;
import com.company.util.abstraction.PersonDTO;
import com.company.util.enamerations.Grade;

public class StudentDTO extends PersonDTO {

    private Grade grade;
    private FacultyDTO faculty;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        setId(student.getId());
        setName(student.getName());
        setSurname(student.getSurname());
        setBirthdate(student.getBirthdate());
        this.grade = student.getGrade();
        this.faculty = new FacultyDTO(student.getFaculty());
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
}
