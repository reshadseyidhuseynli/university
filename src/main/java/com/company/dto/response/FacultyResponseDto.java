package com.company.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class FacultyResponseDto {

    private Integer id;
    private String name;
    private TeacherResponseDto head;
    private List<SubjectResponseDto> subjects;
    private List<TeacherResponseDto> teachers;
    private List<StudentResponseDto> students;

}
