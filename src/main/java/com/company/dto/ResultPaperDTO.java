package com.company.dto;

import java.util.List;

public class ResultPaperDTO {
    private StudentDTO student;
    private TeacherDTO teacher;
    private LessonDTO lesson;
    private List<QuestionDTO> questions;
    private Integer[] answers;
    private int trueAnswerCount;
    private int falseAnswerCount;

    public ResultPaperDTO() {
    }

    public ResultPaperDTO(StudentDTO student, TeacherDTO teacher, LessonDTO lesson, List<QuestionDTO> questions) {
        this.student = student;
        this.teacher = teacher;
        this.lesson = lesson;
        this.questions = questions;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

    public LessonDTO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestion(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public Integer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Integer[] answers) {
        this.answers = answers;
    }

    public int getTrueAnswerCount() {
        return trueAnswerCount;
    }

    public void setTrueAnswerCount(int trueAnswerCount) {
        this.trueAnswerCount = trueAnswerCount;
    }

    public int getFalseAnswerCount() {
        return falseAnswerCount;
    }

    public void setFalseAnswerCount(int falseAnswerCount) {
        this.falseAnswerCount = falseAnswerCount;
    }
}
