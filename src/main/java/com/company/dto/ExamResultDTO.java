package com.company.dto;

import com.company.entity.ExamResult;

public class ExamResultDTO {
    private Integer id;
    private StudentDTO student;
    private TeacherDTO teacher;
    private LessonDTO lesson;
    private int trueAnswerCount;
    private int falseAnswerCount;

    public ExamResultDTO() {
    }

    public ExamResultDTO(StudentDTO student, TeacherDTO teacher, LessonDTO lesson) {
        this.student = student;
        this.teacher = teacher;
        this.lesson = lesson;

    }

    public ExamResultDTO(ExamResult examResult){
        this.student = new StudentDTO(examResult.getStudent());
        this.teacher = new TeacherDTO(examResult.getTeacher());
        this.lesson = new LessonDTO(examResult.getLesson());
        this.trueAnswerCount = examResult.getTrueAnswerCount();
        this.falseAnswerCount = examResult.getFalseAnswerCount();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
