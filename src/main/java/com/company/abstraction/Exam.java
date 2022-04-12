package com.company.abstraction;

import com.company.entity.Question;

public interface Exam {
    Question selectQuestion();
    boolean giveAnswer(Integer answer);
    int calculateScore();
}
