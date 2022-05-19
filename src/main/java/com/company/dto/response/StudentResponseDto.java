package com.company.dto.response;

import com.company.domain.Grade;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentResponseDto {

    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Grade grade;
    private FacultyResponseDto faculty;
    private List<ExamResultResponseDto> examResults;

}
