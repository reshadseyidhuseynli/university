package com.company.util.abstraction;

import com.company.database.entity.Question;
import com.company.dto.ResultPaperDTO;

public interface Exam {
    Question selectQuestion(int i);
    boolean giveAnswer(Integer answer);
    ResultPaperDTO stopAndGiveResult();
}
