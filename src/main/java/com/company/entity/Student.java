package com.company.entity;

import com.company.domain.Grade;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "grade")
    private Grade grade;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;
    @OneToMany(mappedBy = "student")
    private List<ExamResult> examResults;

    public Student(Faculty faculty,
                   String name,
                   String surname,
                   LocalDate birthdate) {

        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.faculty = faculty;
        this.grade = Grade.BACHELOR_I;
    }

}
