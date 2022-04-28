package com.company.entity;

import com.company.enamerations.AcademicRank;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "faculty")
public class Faculty implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "head_id", referencedColumnName = "id")
    @OneToOne
    private Teacher head;
    @OneToMany(mappedBy = "faculty")
    private List<Lesson> lessons;
    @OneToMany(mappedBy = "faculty")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "faculty")
    private List<Student> students;


    public Faculty(){
    }

    public Faculty(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
