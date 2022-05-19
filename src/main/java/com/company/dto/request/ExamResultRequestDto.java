package com.company.dto.request;

import lombok.Data;

@Data
public class ExamResultRequestDto {

    Integer teacherId;
    Integer trueAnswerCount;
    Integer falseAnswerCount;

}
