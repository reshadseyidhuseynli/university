package com.company.dto;

import com.company.enamerations.AcademicRank;
import com.company.entity.JoinTeacherStudent;
import com.company.entity.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherDTO {

    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private AcademicRank rank;
    private FacultyDTO faculty;
    private List<StudentDTO> students;
    private LessonDTO lesson;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher) {
        setId(teacher.getId());
        setName(teacher.getName());
        setSurname(teacher.getSurname());
        setBirthdate(teacher.getBirthdate());
        this.rank = teacher.getAcademicRank();
        this.faculty = new FacultyDTO(teacher.getFaculty());

        this.students = new ArrayList<>();
        for (JoinTeacherStudent jts : teacher.getStudents()) {
            students.add(new StudentDTO(jts.getStudent()));
        }

        this.lesson = new LessonDTO(teacher.getLesson());
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

    public AcademicRank getRank() {
        return rank;
    }

    public void setRank(AcademicRank rank) {
        this.rank = rank;
    }

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public LessonDTO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }
}
