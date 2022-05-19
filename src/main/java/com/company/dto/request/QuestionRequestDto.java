package com.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto {

    String text;
    String option1;
    String option2;
    String option3;
    String option4;
    Integer trueOption;

}