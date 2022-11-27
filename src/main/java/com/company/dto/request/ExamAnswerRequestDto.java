package com.company.dto.request;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ExamAnswerRequestDto {

    private Integer studentId;
    private Integer subjectId;

    @Size(min = 10, max = 10)
    private List<Integer> questionsIds;
    private List<Integer> answers;

}
