package com.company.dto.response;

import lombok.Data;

@Data
public class ExamResultResponseDto {

    private Integer id;
    private SubjectResponseDto subject;
    private Integer countOfTrueAnswers;

    public static ExamResultResponseDto of(SubjectResponseDto subject,
                                           Integer countOfTrueAnswers) {
        ExamResultResponseDto result = new ExamResultResponseDto();
        result.subject = subject;
        result.countOfTrueAnswers = countOfTrueAnswers;
        return result;
    }
}
