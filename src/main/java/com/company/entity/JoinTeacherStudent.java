package com.company.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "join_teacher_student")
public class JoinTeacherStudent implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacher;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private Student student;

    protected JoinTeacherStudent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
