package com.company.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exam_result")
public class ExamResult implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private Student student;
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacher;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne
    private Lesson lesson;
    @Column(name = "true_answer_count")
    private Integer trueAnswerCount;
    @Column(name = "false_answer_count")
    private Integer falseAnswerCount;

    public ExamResult() {
    }

    public ExamResult(Student student, Teacher teacher, Integer trueAnswerCount, Integer falseAnswerCount){
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Integer getTrueAnswerCount() {
        return trueAnswerCount;
    }

    public void setTrueAnswerCount(Integer trueAnswerCount) {
        this.trueAnswerCount = trueAnswerCount;
    }

    public Integer getFalseAnswerCount() {
        return falseAnswerCount;
    }

    public void setFalseAnswerCount(Integer falseAnswerCount) {
        this.falseAnswerCount = falseAnswerCount;
    }
}
