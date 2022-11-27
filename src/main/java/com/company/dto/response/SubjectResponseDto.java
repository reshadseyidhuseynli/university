package com.company.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SubjectResponseDto {

    private Integer id;
    private String name;
    private List<QuestionWithoutAnswerResponseDto> questions;

}
