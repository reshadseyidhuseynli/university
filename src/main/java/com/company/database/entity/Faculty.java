package com.company.database.entity;

import com.company.util.abstraction.IdentifierEntity;
import com.company.util.enamerations.AcademicRank;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty extends IdentifierEntity implements Serializable {
    @JoinColumn(name = "head_id", referencedColumnName = "id")
    @OneToOne
    private Teacher head;

    @OneToMany(mappedBy = "faculty")
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "faculty")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "faculty")
    private List<Student> students;
    @OneToMany(mappedBy = "faculty")
    private List<Question> questions;

    public Faculty(){
    }

    public Faculty(int id, String name) {
        super(id,name);
    }

    public Teacher getHead() {
        return head;
    }

    public boolean setHead(Teacher head) {
        if (head.getAcademicRank() == AcademicRank.PROFESSOR) {
            this.head = head;
            return true;
        }
        return false;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Entity.Department{" +
                "id:" + getId() +
                ", " + getName() +
                ", lessons:" + lessons +
                ", teachers:" + teachers +
                '}';
    }
}
