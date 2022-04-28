package com.company.exam;

import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.dto.ExamResultDTO;
import com.company.entity.Question;

public class ExamRoom extends AbstractExam {
    private Integer currentQuestionNumber;
    private final Integer[] answers;

    public ExamRoom(Student student, Teacher teacher) {
        super(student, teacher);
        currentQuestionNumber = 0;
        answers = new Integer[examinationPaper.size()];
    }

    @Override
    public Question selectQuestion(int number) {
        this.currentQuestionNumber = number;
        return examinationPaper.get(number);
    }

    @Override
    public void giveAnswer(Integer answer) {
        answers[currentQuestionNumber] = answer;
    }

    @Override
    public ExamResultDTO acceptAnswersAndGiveResult(){
        for (int i = 0; i < examinationPaper.size(); i++){
            if (answers[i].equals(examinationPaper.get(i).getTrueOption()))
                resultDTO.setTrueAnswerCount(resultDTO.getTrueAnswerCount()+1);
            else
                resultDTO.setFalseAnswerCount(resultDTO.getFalseAnswerCount()+1);
        }
        return resultDTO;
    }
}
