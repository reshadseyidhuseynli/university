package com.company.dto;

import java.util.List;

public class FacultyDto {

    private Integer id;
    private String name;
    private TeacherDto head;
    private List<LessonDto> lessons;
    private List<TeacherDto> teachers;
    private List<StudentDto> students;

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

    public TeacherDto getHead() {
        return head;
    }

    public void setHead(TeacherDto head) {
        this.head = head;
    }

    public List<LessonDto> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDto> lessons) {
        this.lessons = lessons;
    }

    public List<TeacherDto> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDto> teachers) {
        this.teachers = teachers;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

}
