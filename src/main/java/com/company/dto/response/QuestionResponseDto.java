package com.company.dto.response;

import lombok.Data;

@Data
public class QuestionResponseDto {

    private Integer id;
    private String text;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer trueOption;

}
