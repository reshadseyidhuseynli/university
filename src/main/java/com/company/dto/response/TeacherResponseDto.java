package com.company.dto.response;

import com.company.domain.AcademicRank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TeacherResponseDto {

    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private AcademicRank rank;
    private FacultyResponseDto faculty;
    private List<StudentResponseDto> students;
    private LessonResponseDto lesson;

}
