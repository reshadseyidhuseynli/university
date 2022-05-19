package com.company.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "join_teacher_student")
public class TeacherStudent implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @ManyToOne
    private Teacher teacher;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private Student student;

}
