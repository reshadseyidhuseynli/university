package com.company.exam;

import com.company.entity.Question;
import com.company.dto.ExamResultDTO;

public interface Exam {
    Question selectQuestion(int i);
    void giveAnswer(Integer answer);
    ExamResultDTO acceptAnswersAndGiveResult();
}