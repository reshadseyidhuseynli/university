package com.company.dto;

import com.company.database.entity.JoinTeacherLesson;
import com.company.database.entity.JoinTeacherStudent;
import com.company.database.entity.Teacher;
import com.company.util.abstraction.PersonDTO;
import com.company.util.enamerations.AcademicRank;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends PersonDTO {

    private AcademicRank rank;
    private FacultyDTO faculty;
    private List<StudentDTO> students;
    private List<LessonDTO> lessons;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher) {
        setId(teacher.getId());
        setName(teacher.getName());
        setSurname(teacher.getSurname());
        setBirthdate(teacher.getBirthdate());
        this.rank = teacher.getAcademicRank();
        this.faculty = new FacultyDTO(teacher.getFaculty());

        this.students = new ArrayList<>();
        for (JoinTeacherStudent jts : teacher.getStudents()){
            students.add(new StudentDTO(jts.getStudent()));
        }

        this.lessons = new ArrayList<>();
        for (JoinTeacherLesson jtl : teacher.getLessons()){
            lessons.add(new LessonDTO(jtl.getLesson()));
        }
    }

    public AcademicRank getRank() {
        return rank;
    }

    public void setRank(AcademicRank rank) {
        this.rank = rank;
    }

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public List<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonDTO> lessons) {
        this.lessons = lessons;
    }
}
