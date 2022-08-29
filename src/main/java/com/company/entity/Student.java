package com.company.entity;

import com.company.domain.Grade;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<ExamResult> examResults;

    public Student() {
        this.grade = Grade.BACHELOR_I;
    }

}
