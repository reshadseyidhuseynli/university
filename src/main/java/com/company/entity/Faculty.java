package com.company.entity;

import com.company.domain.AcademicRank;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
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

    public Faculty(String name) {
        this.name = name;
    }

    public boolean setHead(Teacher head) {

        if (head.getAcademicRank() == AcademicRank.PROFESSOR) {
            this.head = head;
            return true;
        } else
            return false;
    }

}
