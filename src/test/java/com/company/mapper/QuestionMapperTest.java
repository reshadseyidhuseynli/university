package com.company.mapper;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.entity.Question;
import com.company.entity.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionMapperTest {

    private static final Integer ID = 1;
    private static final Integer TRUE_OPTION = 1;
    private static final String QUESTION_TEXT = "questionText";
    private static final String QUESTION_OPTION1 = "questionOption1";
    private static final String QUESTION_OPTION2 = "questionOption2";
    private static final String QUESTION_OPTION3 = "questionOption3";
    private static final String QUESTION_OPTION4 = "questionOption4";

    private static Question question;
    private static QuestionResponseDto questionResponseDto;
    private static QuestionWithoutAnswerResponseDto questionWithoutAnswerResponseDto;
    private static QuestionRequestDto questionRequestDto;
    private static Question newQuestion;

    private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;

    @BeforeAll
    public static void init() {
        question = new Question();
        question.setId(ID);
        question.setText(QUESTION_TEXT);
        question.setOption1(QUESTION_OPTION1);
        question.setOption2(QUESTION_OPTION2);
        question.setOption3(QUESTION_OPTION3);
        question.setOption4(QUESTION_OPTION4);
        question.setTrueOption(TRUE_OPTION);
        question.setSubject(new Subject());

        questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setId(ID);
        questionResponseDto.setText(QUESTION_TEXT);
        questionResponseDto.setOption1(QUESTION_OPTION1);
        questionResponseDto.setOption2(QUESTION_OPTION2);
        questionResponseDto.setOption3(QUESTION_OPTION3);
        questionResponseDto.setOption4("questionOption4");
        questionResponseDto.setTrueOption(TRUE_OPTION);

        questionWithoutAnswerResponseDto = new QuestionWithoutAnswerResponseDto();
        questionWithoutAnswerResponseDto.setId(ID);
        questionWithoutAnswerResponseDto.setText(QUESTION_TEXT);
        questionWithoutAnswerResponseDto.setOption1(QUESTION_OPTION1);
        questionWithoutAnswerResponseDto.setOption2(QUESTION_OPTION2);
        questionWithoutAnswerResponseDto.setOption3(QUESTION_OPTION3);
        questionWithoutAnswerResponseDto.setOption4(QUESTION_OPTION4);

        questionRequestDto = new QuestionRequestDto();
        questionRequestDto.setText(QUESTION_TEXT);
        questionRequestDto.setOption1(QUESTION_OPTION1);
        questionRequestDto.setOption2(QUESTION_OPTION2);
        questionRequestDto.setOption3(QUESTION_OPTION3);
        questionRequestDto.setOption4(QUESTION_OPTION4);
        questionRequestDto.setTrueOption(TRUE_OPTION);

        newQuestion = new Question();
        newQuestion.setId(ID);
        newQuestion.setText(QUESTION_TEXT);
        newQuestion.setOption1(QUESTION_OPTION1);
        newQuestion.setOption2(QUESTION_OPTION2);
        newQuestion.setOption3(QUESTION_OPTION3);
        newQuestion.setOption4(QUESTION_OPTION4);
        newQuestion.setTrueOption(TRUE_OPTION);
    }

    @Test
    void toQuestionDtoTest() {
        Question given = question;
        QuestionResponseDto expected = questionResponseDto;
        QuestionResponseDto actual = questionMapper.toQuestionDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toQuestionDtoListTest() {
        List<Question> given = Collections.singletonList(question);
        List<QuestionResponseDto> expected = Collections.singletonList(questionResponseDto);
        List<QuestionResponseDto> actual = questionMapper.toQuestionDtoList(given);
        assertEquals(expected, actual);
    }

    @Test
    void toQuestionTest() {
        QuestionRequestDto given = questionRequestDto;
        Question expected = newQuestion;

        Question actual = questionMapper.toQuestion(given);
        actual.setId(ID);
        assertEquals(expected, actual);
    }

    @Test
    void toQuestionListTest() {
        List<QuestionRequestDto> given = Collections.singletonList(questionRequestDto);
        List<Question> expected = Collections.singletonList(newQuestion);

        List<Question> actual = questionMapper.toQuestionList(given);
        actual.get(0).setId(ID);
        assertEquals(expected, actual);
    }

    @Test
    void toQuestionWithoutAnswerDtoTest() {
        Question given = question;
        QuestionWithoutAnswerResponseDto expected = questionWithoutAnswerResponseDto;
        QuestionWithoutAnswerResponseDto actual =
                questionMapper.toQuestionWithoutAnswerDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toQuestionWithoutAnswerDtoListTest() {
        List<Question> given = Collections.singletonList(question);
        List<QuestionWithoutAnswerResponseDto> expected =
                Collections.singletonList(questionWithoutAnswerResponseDto);
        List<QuestionWithoutAnswerResponseDto> actual =
                questionMapper.toQuestionWithoutAnswerDtoList(given);
        assertEquals(expected, actual);
    }
}
