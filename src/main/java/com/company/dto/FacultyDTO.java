package com.company.dto;

import com.company.database.entity.*;
import com.company.util.abstraction.IdentifierDTO;
import java.util.ArrayList;
import java.util.List;

public class FacultyDTO extends IdentifierDTO {

    private TeacherDTO head;
    private List<LessonDTO> lessons;
    private List<TeacherDTO> teachers;
    private List<StudentDTO> students;
    private List<QuestionDTO> questions;

    public FacultyDTO() {
    }

    public FacultyDTO(Faculty faculty) {
        setId(faculty.getId());
        setName(faculty.getName());
        this.head = new TeacherDTO(faculty.getHead());

        this.lessons = new ArrayList<>();
        List<Lesson> lessonList = faculty.getLessons();
        for (Lesson l : lessonList){
            lessons.add(new LessonDTO(l));
        }

        this.teachers = new ArrayList<>();
        for (Teacher t : faculty.getTeachers()){
            teachers.add(new TeacherDTO(t));
        }

        this.students = new ArrayList<>();
        for (Student s : faculty.getStudents()){
            students.add(new StudentDTO(s));
        }

        this.questions = new ArrayList<>();
        for (Question q : faculty.getQuestions()){
            questions.add(new QuestionDTO(q));
        }
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

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
