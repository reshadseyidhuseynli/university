package com.company.dto;

import java.util.List;

public class LessonDto {

    private Integer id;
    private String name;
    private FacultyDto faculty;
    private List<QuestionDto> questions;

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

    public FacultyDto getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDto faculty) {
        this.faculty = faculty;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

}
