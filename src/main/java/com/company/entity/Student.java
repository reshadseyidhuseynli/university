package com.company.entity;

import com.company.domain.Grade;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return id != null && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
