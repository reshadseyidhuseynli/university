package com.company.dto;

import com.company.database.entity.Lesson;
import com.company.database.entity.Question;
import com.company.util.abstraction.IdentifierDTO;

import java.util.ArrayList;
import java.util.List;

public class LessonDTO extends IdentifierDTO {

    private FacultyDTO faculty;
    private List<QuestionDTO> questions;

    public LessonDTO() {
    }

    public LessonDTO(Lesson lesson) {
        setId(lesson.getId());
        setName(lesson.getName());
        this.faculty = new FacultyDTO(lesson.getFaculty());

        this.questions = new ArrayList<>();
        for (Question q : lesson.getQuestions()){
            questions.add(new QuestionDTO(q));
        }
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
