package com.company.department;

import com.company.abstraction.AbstractExam;
import com.company.entity.Question;
import java.util.Random;

public class ExamRoom extends AbstractExam {
    private Question lastQuestion;
    private final Random random = new Random();

    private boolean checkAnswer(Integer answer){
        return answer.equals(lastQuestion.getTrueOption());
    }

    @Override
    public Question selectQuestion() {
        int questionNumber = random.nextInt(questions.length-1);
        this.lastQuestion = questions[questionNumber];
        return this.lastQuestion;
    }

    @Override
    public boolean giveAnswer(Integer answer) {
        if (checkAnswer(answer)){
            score++;
            return true;
        }
        return false;
    }

    @Override
    public int calculateScore() {
        return this.score;
    }
}
