package com.company.entity;

import com.company.domain.AcademicRank;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {
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
    @Column(name = "academic_rank")
    private AcademicRank academicRank;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne
    private Lesson lesson;
    @OneToMany(mappedBy = "teacher")
    private List<TeacherStudent> students;

    public Teacher(Faculty faculty,
                   String name,
                   String surname,
                   LocalDate birthdate) {

        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.faculty = faculty;
    }

}
