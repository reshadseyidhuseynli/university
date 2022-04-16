package com.company.database.entity;

import com.company.util.abstraction.PersonEntity;
import com.company.util.enamerations.AcademicRank;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher extends PersonEntity implements Serializable {

    @Column(name = "academic_rank")
    private AcademicRank academicRank;

    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "teacher")
    private List<JoinTeacherStudent> students;
    @OneToMany(mappedBy = "teacher")
    private List<JoinTeacherLesson> lessons;

    public Teacher(){
    }
    public Teacher(String name, String surname, LocalDate birthdate, Faculty faculty) {
        super(name, surname, birthdate);
        this.faculty = faculty;
    }

    public AcademicRank getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(AcademicRank academicRank) {
        this.academicRank = academicRank;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<JoinTeacherStudent> getStudents() {
        return students;
    }

    public void setStudents(List<JoinTeacherStudent> students) {
        this.students = students;
    }

    public List<JoinTeacherLesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<JoinTeacherLesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Entity.Teacher{" +
                "id:" + getId() +
                ", " + getName() + " " + getSurname() +
                ", " + academicRank +
                ", department:" + faculty.getName() +
                '}';
    }
}
