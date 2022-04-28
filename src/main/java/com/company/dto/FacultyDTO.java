package com.company.dto;

import com.company.entity.Faculty;
import com.company.entity.Lesson;
import com.company.entity.Student;
import com.company.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class FacultyDTO {

    private Integer id;
    private String name;
    private TeacherDTO head;
    private List<LessonDTO> lessons;
    private List<TeacherDTO> teachers;
    private List<StudentDTO> students;

    public FacultyDTO() {
    }

    public FacultyDTO(Faculty faculty) {
        setId(faculty.getId());
        setName(faculty.getName());
        this.head = new TeacherDTO(faculty.getHead());

        this.lessons = new ArrayList<>();
        List<Lesson> lessonList = faculty.getLessons();
        for (Lesson l : lessonList) {
            lessons.add(new LessonDTO(l));
        }

        this.teachers = new ArrayList<>();
        for (Teacher t : faculty.getTeachers()) {
            teachers.add(new TeacherDTO(t));
        }

        this.students = new ArrayList<>();
        for (Student s : faculty.getStudents()) {
            students.add(new StudentDTO(s));
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

    public TeacherDTO getHead() {
        return head;
    }

    public void setHead(TeacherDTO head) {
        this.head = head;
    }

    public List<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDTO> lessons) {
        this.lessons = lessons;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
