package com.company.mapper;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.entity.Lesson;
import com.company.entity.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class QuestionMapperTest {

    private final QuestionMapper questionMapper = QuestionMapper.INSTANCE;

    private static Question question;
    private static QuestionResponseDto questionResponseDto;
    private static QuestionWithoutAnswerResponseDto questionWithoutAnswerResponseDto;

    @BeforeAll
    private static void init() {
        question = new Question();
        question.setId(1);
        question.setText("questionText");
        question.setOption1("questionOption1");
        question.setOption2("questionOption2");
        question.setOption3("questionOption3");
        question.setOption4("questionOption4");
        question.setTrueOption(1);
        question.setLesson(new Lesson());

        questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setId(1);
        questionResponseDto.setText("questionText");
        questionResponseDto.setOption1("questionOption1");
        questionResponseDto.setOption2("questionOption2");
        questionResponseDto.setOption3("questionOption3");
        questionResponseDto.setOption4("questionOption4");
        questionResponseDto.setTrueOption(1);

        questionWithoutAnswerResponseDto = new QuestionWithoutAnswerResponseDto();
        questionWithoutAnswerResponseDto.setId(1);
        questionWithoutAnswerResponseDto.setText("questionText");
        questionWithoutAnswerResponseDto.setOption1("questionOption1");
        questionWithoutAnswerResponseDto.setOption2("questionOption2");
        questionWithoutAnswerResponseDto.setOption3("questionOption3");
        questionWithoutAnswerResponseDto.setOption4("questionOption4");
    }

    @Test
    void toQuestionDtoTest() {
        QuestionResponseDto actual = questionMapper.toQuestionDto(question);

        Assertions.assertEquals(questionResponseDto, actual);
    }

    @Test
    void toQuestionDtoListTest() {
        List<Question> given = new ArrayList<>();
        given.add(question);

        List<QuestionResponseDto> expected = new ArrayList<>();
        expected.add(questionResponseDto);

        List<QuestionResponseDto> actual = questionMapper.toQuestionDtoList(given);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toQuestionTest() {
        QuestionRequestDto given = new QuestionRequestDto();
        given.setText("testQuestionTest");
        given.setOption1("testQuestionOption1");
        given.setOption2("testQuestionOption2");
        given.setOption3("testQuestionOption3");
        given.setOption4("testQuestionOption4");
        given.setTrueOption(1);

        Question expected = new Question();
        expected.setId(1000);
        expected.setText("testQuestionTest");
        expected.setOption1("testQuestionOption1");
        expected.setOption2("testQuestionOption2");
        expected.setOption3("testQuestionOption3");
        expected.setOption4("testQuestionOption4");
        expected.setTrueOption(1);

        Question actual = questionMapper.toQuestion(given);
        actual.setId(1000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toQuestionListTest() {
        QuestionRequestDto questionRequestDto = new QuestionRequestDto();
        questionRequestDto.setText("testQuestionTest");
        questionRequestDto.setOption1("testQuestionOption1");
        questionRequestDto.setOption2("testQuestionOption2");
        questionRequestDto.setOption3("testQuestionOption3");
        questionRequestDto.setOption4("testQuestionOption4");
        questionRequestDto.setTrueOption(1);

        Question question = new Question();
        question.setId(1000);
        question.setText("testQuestionTest");
        question.setOption1("testQuestionOption1");
        question.setOption2("testQuestionOption2");
        question.setOption3("testQuestionOption3");
        question.setOption4("testQuestionOption4");
        question.setTrueOption(1);

        List<QuestionRequestDto> given = new ArrayList<>();
        given.add(questionRequestDto);

        List<Question> expected = new ArrayList<>();
        expected.add(question);

        List<Question> actual = questionMapper.toQuestionList(given);
        actual.get(0).setId(1000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toQuestionWithoutAnswerDtoTest() {
        QuestionWithoutAnswerResponseDto actual =
                questionMapper.toQuestionWithoutAnswerDto(question);

        Assertions.assertEquals(questionWithoutAnswerResponseDto, actual);
    }

    @Test
    void toQuestionWithoutAnswerDtoListTest() {
        List<Question> given = new ArrayList<>();
        given.add(question);

        List<QuestionWithoutAnswerResponseDto> expected = new ArrayList<>();
        expected.add(questionWithoutAnswerResponseDto);

        List<QuestionWithoutAnswerResponseDto> actual =
                questionMapper.toQuestionWithoutAnswerDtoList(given);

        Assertions.assertEquals(expected, actual);
    }
}
