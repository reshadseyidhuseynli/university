package com.company.dto;

import com.company.entity.Lesson;
import com.company.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class LessonDTO {

    private Integer id;
    private String name;
    private FacultyDTO faculty;
    private List<QuestionDTO> questions;

    public LessonDTO() {
    }

    public LessonDTO(Lesson lesson) {
        setId(lesson.getId());
        setName(lesson.getName());
        this.faculty = new FacultyDTO(lesson.getFaculty());

        this.questions = new ArrayList<>();
        for (Question q : lesson.getQuestions()) {
            questions.add(new QuestionDTO(q));
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

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
