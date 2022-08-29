package com.company.service;

import com.company.dto.request.ExamAnswerRequestDto;
import com.company.dto.response.*;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {

    private final TeacherService teacherService;
    private final QuestionService questionService;
    private final Random random = new Random();

    public List<QuestionWithoutAnswerResponseDto> getExaminationPaperByTeacherId(Integer id) {
        final TeacherResponseDto teacherDto = teacherService.getById(id);

        List<QuestionWithoutAnswerResponseDto> allQuestionsInLesson = teacherDto.getLesson().getQuestions();
        int allQuestionsCount = allQuestionsInLesson.size();

        int questionCountInExaminationPaper = Math.min(allQuestionsCount, 10);

        List<Integer> randomNumbers = new ArrayList<>();
        do {
            final int newNumber = random.nextInt(allQuestionsCount);

            if (!randomNumbers.contains(newNumber)) {
                randomNumbers.add(newNumber);
            }
        } while (randomNumbers.size() != questionCountInExaminationPaper);

        List<QuestionWithoutAnswerResponseDto> examinationPaper = new ArrayList<>();
        for (Integer number : randomNumbers) {
            examinationPaper.add(allQuestionsInLesson.get(number));
        }

        return examinationPaper;
    }

    public ExamResultResponseDto acceptAnswersAndGiveResult(ExamAnswerRequestDto requestDto) {

        Integer teacherId = requestDto.getTeacherId();
        Integer[] questionIds = requestDto.getQuestionsId();
        Integer[] answers = requestDto.getAnswers();

        if (questionIds.length != answers.length) {
            throw new ServiceException(
                    ErrorCode.THE_COUNT_OF_ANSWERS_IS_INCORRECT,
                    "The count of answers is incorrect");
        }

        final int questionCountInExaminationPaper = questionIds.length;

        final TeacherResponseDto teacherResponseDto = teacherService.getById(teacherId);

        final List<QuestionResponseDto> questions = getQuestionListByIds(questionIds);

        int trueAnswerCount = 0;
        int falseAnswerCount = 0;

        for (int i = 0; i < questionCountInExaminationPaper; i++) {
            if (questions.get(i).getTrueOption().equals(answers[i]))
                trueAnswerCount++;
            else
                falseAnswerCount++;
        }

        return new ExamResultResponseDto(
                teacherResponseDto,
                trueAnswerCount,
                falseAnswerCount);
    }

    private List<QuestionResponseDto> getQuestionListByIds(Integer[] questionsIds) {
        List<QuestionResponseDto> questions = new ArrayList<>();
        for (Integer questionId : questionsIds) {
            QuestionResponseDto question = questionService.getById(questionId);
            questions.add(question);
        }

        return questions;
    }

}
