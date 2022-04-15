package com.company.dto;

import com.company.database.entity.Lesson;
import com.company.util.abstraction.IdentifierDTO;

public class LessonDTO extends IdentifierDTO {

    private FacultyDTO faculty;

    public LessonDTO() {
    }

    public LessonDTO(Lesson lesson) {
        setId(lesson.getId());
        setName(lesson.getName());
        this.faculty = new FacultyDTO(lesson.getFaculty());
    }

    public FacultyDTO getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyDTO faculty) {
        this.faculty = faculty;
    }
}
