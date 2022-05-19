package com.company.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
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

    public ExamResult(Student student, Teacher teacher, Integer trueAnswerCount, Integer falseAnswerCount){
        
        this.student = student;
        this.teacher = teacher;
        this.lesson = teacher.getLesson();
        this.trueAnswerCount = trueAnswerCount;
        this.falseAnswerCount = falseAnswerCount;
    }

}
