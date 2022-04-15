package com.company.util.abstraction;

import com.company.database.entity.Question;

public interface Exam {
    Question selectQuestion();
    boolean giveAnswer(Integer answer);
    int calculateScore();
}
