package com.company.service;

import com.company.dto.request.ExamAnswerRequestDto;
import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {

    private static final Integer QUESTION_COUNT = 10;

    private final QuestionService questionService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    public List<QuestionWithoutAnswerResponseDto> getExaminationPaperBySubjectId(Integer subjectId) {
        return questionService.getRandomQuestions(subjectId, QUESTION_COUNT);
    }

    public ExamResultResponseDto acceptAnswersAndGiveResult(ExamAnswerRequestDto requestDto) {
        Integer countOfTrueAnswer = checkAnswers(
                questionService.getByIdList(requestDto.getQuestionsIds()),
                requestDto.getAnswers());

        studentService.addExamResultForStudent(
                requestDto.getStudentId(),
                ExamResultRequestDto.of(requestDto.getSubjectId(), countOfTrueAnswer));

        return ExamResultResponseDto.of(
                subjectService.getById(requestDto.getSubjectId()),
                countOfTrueAnswer);
    }

    private Integer checkAnswers(
            List<QuestionResponseDto> questions,
            List<Integer> answers) {

        int countOfTrueAnswer = 0;
        for (int i = 0; i < QUESTION_COUNT; i++) {
            if (questions.get(i).getTrueOption().equals(answers.get(i))) {
                countOfTrueAnswer++;
            }
        }
        return countOfTrueAnswer;
    }

}
