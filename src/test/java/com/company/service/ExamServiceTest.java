package com.company.service;

import com.company.dto.request.ExamAnswerRequestDto;
import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.dto.response.SubjectResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceTest {

    private static final Integer QUESTION_COUNT = 10;
    private static final Integer SUBJECT_ID = 1;

    private static ExamAnswerRequestDto examAnswerRequestDto;
    private static List<QuestionResponseDto> questions;
    private static SubjectResponseDto subject;
    private static Integer trueAnswerCount;
    private static ExamResultRequestDto resultRequestDto;


    @Mock
    private SubjectService subjectService;

    @Mock
    private QuestionService questionService;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private ExamService examService;

    @BeforeAll
    static void init() {
        examAnswerRequestDto = new ExamAnswerRequestDto();
        examAnswerRequestDto.setStudentId(1);
        examAnswerRequestDto.setSubjectId(SUBJECT_ID);
        examAnswerRequestDto.setQuestionsIds(createDefaultQuestionIds());
        examAnswerRequestDto.setAnswers(createDefaultAnswers());

        questions = new ArrayList<>();
        QuestionResponseDto question = new QuestionResponseDto();
        question.setTrueOption(1);
        for (int i = 0; i < 10; i++) {
            questions.add(question);
        }

        subject = new SubjectResponseDto();
        trueAnswerCount = 10;
        resultRequestDto = ExamResultRequestDto
                .of(SUBJECT_ID, trueAnswerCount);
    }

    @Test
    void getExaminationPaperBySubjectId() {
        Integer givenSubjectId = SUBJECT_ID;
        List<QuestionWithoutAnswerResponseDto> expected = new ArrayList<>();

        when(questionService.getRandomQuestions(givenSubjectId, QUESTION_COUNT))
                .thenReturn(expected);
        List<QuestionWithoutAnswerResponseDto> actual =
                examService.getExaminationPaperBySubjectId(givenSubjectId);

        assertEquals(expected, actual);
        verify(questionService, times(1))
                .getRandomQuestions(givenSubjectId, QUESTION_COUNT);
    }

    @Test
    void acceptAnswersAndGiveResult_Success() {
        ExamAnswerRequestDto given = examAnswerRequestDto;
        ExamResultResponseDto expected = ExamResultResponseDto.of(subject, trueAnswerCount);

        when(questionService.getByIdList(given.getQuestionsIds()))
                .thenReturn(questions);
        when(subjectService.getById(given.getSubjectId()))
                .thenReturn(subject);

        ExamResultResponseDto actual = examService.acceptAnswersAndGiveResult(given);
        assertEquals(expected, actual);
        verify(questionService, times(1))
                .getByIdList(given.getQuestionsIds());
        verify(studentService, times(1))
                .addExamResultForStudent(given.getStudentId(), resultRequestDto);
    }

    private static List<Integer> createDefaultQuestionIds() {
        List<Integer> questionIds = new ArrayList<>();
        for (int i = 0; i < 10; ) {
            questionIds.add(++i);
        }
        return questionIds;
    }

    private static List<Integer> createDefaultAnswers() {
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            answers.add(1);
        }
        return answers;
    }

}
