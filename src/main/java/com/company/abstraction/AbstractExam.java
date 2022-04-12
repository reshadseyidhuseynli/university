package com.company.abstraction;

import com.company.entity.Question;

public abstract class AbstractExam implements Exam {
    protected Question[] questions;
    protected int score;

    protected AbstractExam() {
        this.questions =  new Question[10];
    }
}
