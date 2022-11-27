package com.company.entity;

import com.company.domain.AcademicRank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "faculty")
public class Faculty implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "head_id", referencedColumnName = "id")
    @OneToOne
    private Teacher head;

    @OneToMany(mappedBy = "faculty")
    @ToString.Exclude
    private List<Subject> subjects;

    @OneToMany(mappedBy = "faculty")
    @ToString.Exclude
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "faculty")
    @ToString.Exclude
    private List<Student> students;

    public Faculty(String name) {
        this.name = name;
    }

    public void setHead(Teacher head) {
        if (head.getAcademicRank() == AcademicRank.PROFESSOR) {
            this.head = head;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Faculty faculty = (Faculty) o;
        return id != null && Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
