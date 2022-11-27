package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "exam_result")
public class ExamResult implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne
    private Student student;

    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne
    private Subject subject;

    @Column(name = "count_of_true_answers")
    private Integer countOfTrueAnswers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        ExamResult that = (ExamResult) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
