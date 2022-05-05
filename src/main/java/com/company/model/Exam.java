package com.company.model;

import com.company.dto.ExamResultDto;
import com.company.dto.QuestionDto;
import com.company.dto.StudentDto;
import com.company.dto.TeacherDto;

import java.util.*;

public class Exam {

    private final StudentDto student;
    private final TeacherDto teacher;
    private final List<QuestionDto> examinationPaper;
    private final Integer[] answers;
    private Integer currentQuestionNumber;

    public Exam(StudentDto student, TeacherDto teacher) {

        this.student = student;
        this.teacher = teacher;
        this.examinationPaper = getExaminationPaper();

        currentQuestionNumber = 0;
        answers = new Integer[examinationPaper.size()];
    }

    private List<QuestionDto> getExaminationPaper() {

        List<QuestionDto> allQuestionsInLesson = teacher.getLesson().getQuestions();
        int questionCountInExaminationPaper = Math.min(allQuestionsInLesson.size(), 10);

        Random random = new Random();
        Set<Integer> randomNumbers = new HashSet<>();

        do
            randomNumbers.add(random.nextInt(allQuestionsInLesson.size() - 1));
        while (randomNumbers.size() != questionCountInExaminationPaper);

        List<Integer> randomNumberList = new ArrayList<>(randomNumbers);

        List<QuestionDto> newExaminationPaper = new ArrayList<>();
        for (int i = 0; i < questionCountInExaminationPaper; i++) {
            examinationPaper.add(allQuestionsInLesson.get(randomNumberList.get(i)));
        }

        return newExaminationPaper;
    }

    public QuestionDto selectQuestion(int number) {

        this.currentQuestionNumber = number;

        return examinationPaper.get(number);

    }

    public void giveAnswer(Integer answer) {

        answers[currentQuestionNumber] = answer;
    }

    public ExamResultDto acceptAnswersAndGiveResult() {

        int trueAnswerCount = 0;
        int falseAnswerCount = 0;

        for (int i = 0; i < examinationPaper.size(); i++) {

            if (answers[i].equals(examinationPaper.get(i).getTrueOption()))
                trueAnswerCount++;
            else
                falseAnswerCount++;
        }

        return new ExamResultDto(student, teacher, trueAnswerCount, falseAnswerCount);

    }

}
