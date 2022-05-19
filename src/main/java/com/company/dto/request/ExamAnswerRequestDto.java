package com.company.dto.request;

import lombok.Data;

@Data
public class ExamAnswerRequestDto {

    Integer studentId;
    Integer teacherId;
    Integer[] questionsId;
    Integer[] answers;

}
