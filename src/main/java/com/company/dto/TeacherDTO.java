package com.company.dto;

import com.company.entity.JoinTeacherStudent;
import com.company.entity.Teacher;
import com.company.util.abstraction.PersonDTO;
import com.company.util.enamerations.AcademicRank;
import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends PersonDTO {

    private AcademicRank rank;
    private FacultyDTO faculty;
    private List<StudentDTO> students;
    private LessonDTO lesson;

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

        this.lesson = new LessonDTO(teacher.getLesson());
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

    public LessonDTO getLesson() {
        return lesson;
    }

    public void setLesson(LessonDTO lesson) {
        this.lesson = lesson;
    }
}
