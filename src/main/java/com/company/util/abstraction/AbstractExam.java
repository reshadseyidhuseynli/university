package com.company.util.abstraction;

import com.company.database.entity.Question;

public abstract class AbstractExam implements Exam {
    protected Question[] questions;
    protected int score;

    protected AbstractExam() {
        this.questions =  new Question[10];
    }
}
