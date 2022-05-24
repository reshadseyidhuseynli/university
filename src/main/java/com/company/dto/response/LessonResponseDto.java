package com.company.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class LessonResponseDto {

    private Integer id;
    private String name;
    private FacultyResponseDto faculty;
    private List<QuestionWithoutAnswerResponseDto> questions;

}
