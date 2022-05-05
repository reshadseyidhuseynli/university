package com.company.dto;

public class ExamResultDto {

    private Integer id;
    private StudentDto student;
    private TeacherDto teacher;
    private LessonDto lesson;
    private Integer trueAnswerCount;
    private Integer falseAnswerCount;

    public ExamResultDto() {
    }

    public ExamResultDto(StudentDto student,
                         TeacherDto teacher,
                         Integer trueAnswerCount,
                         Integer falseAnswerCount) {

        this.student = student;
        this.teacher = teacher;
        this.lesson = teacher.getLesson();
        this.trueAnswerCount = trueAnswerCount;
        this.falseAnswerCount = falseAnswerCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public LessonDto getLesson() {
        return lesson;
    }

    public void setLesson(LessonDto lesson) {
        this.lesson = lesson;
    }

    public int getTrueAnswerCount() {
        return trueAnswerCount;
    }

    public void setTrueAnswerCount(Integer trueAnswerCount) {
        this.trueAnswerCount = trueAnswerCount;
    }

    public int getFalseAnswerCount() {
        return falseAnswerCount;
    }

    public void setFalseAnswerCount(Integer falseAnswerCount) {
        this.falseAnswerCount = falseAnswerCount;
    }

}
