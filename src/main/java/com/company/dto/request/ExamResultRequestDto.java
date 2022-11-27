package com.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultRequestDto {

    private Integer subjectId;
    private Integer trueAnswerCount;

    public static ExamResultRequestDto of(Integer subjectId, Integer trueAnswerCount) {
        ExamResultRequestDto request = new ExamResultRequestDto();
        request.setSubjectId(subjectId);
        request.setTrueAnswerCount(trueAnswerCount);
        return request;
    }

}
