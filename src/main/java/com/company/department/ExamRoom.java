package com.company.department;

import com.company.database.entity.Student;
import com.company.database.entity.Teacher;
import com.company.dto.ResultPaperDTO;
import com.company.util.abstraction.AbstractExam;
import com.company.database.entity.Question;

public class ExamRoom extends AbstractExam {
    private Question currentQuestion;
    private Integer currentQuestionNumber;
    private final Integer[] answers;

    public ExamRoom(Student student, Teacher teacher) {
        super(student, teacher);
        currentQuestionNumber = 0;
        answers = new Integer[examinationPaper.size()];
    }

    private boolean checkAnswer(Integer answer){
        return answer.equals(currentQuestion.getTrueOption());
    }

    @Override
    public Question selectQuestion(int number) {
        this.currentQuestion = examinationPaper.get(number);
        this.currentQuestionNumber = number;
        return this.currentQuestion;
    }

    @Override
    public boolean giveAnswer(Integer answer) {
        answers[currentQuestionNumber] = answer;
        if (checkAnswer(answer)){
            resultDTO.setTrueAnswerCount(resultDTO.getTrueAnswerCount()+1);
            return true;
        }else {
            resultDTO.setFalseAnswerCount(resultDTO.getFalseAnswerCount()+1);
            return false;
        }
    }

    @Override
    public ResultPaperDTO stopAndGiveResult() {
        resultDTO.setAnswers(answers);
        return resultDTO;
    }
}
