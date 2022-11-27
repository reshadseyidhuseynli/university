package com.company.service;

import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.entity.Question;
import com.company.error.ServiceException;
import com.company.mapper.QuestionMapper;
import com.company.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionMapper questionMapper;
    @InjectMocks
    private QuestionService questionService;

    @Test
    void getAllTest() {
        List<Question> questionList = new ArrayList<>();
        List<QuestionResponseDto> expected = new ArrayList<>();

        Mockito.when(questionRepository.findAll())
                .thenReturn(questionList);
        Mockito.when(questionMapper.toQuestionDtoList(questionList))
                .thenReturn(expected);

        List<QuestionResponseDto> actual = questionService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(questionRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(questionMapper, Mockito.times(1))
                .toQuestionDtoList(questionList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;
        Question question = new Question();
        QuestionResponseDto expected = new QuestionResponseDto();

        Mockito.when(questionRepository.findById(given))
                .thenReturn(Optional.of(question));
        Mockito.when(questionMapper.toQuestionDto(question))
                .thenReturn(expected);

        QuestionResponseDto actual = questionService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(questionRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(questionMapper, Mockito.times(1))
                .toQuestionDto(question);
    }

    @Test
    void getByIdNotFoundTest() {
        Integer given = 100;
        Question question = new Question();

        Mockito.when(questionRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> questionService.getById(given));
        Mockito.verify(questionRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(questionMapper, Mockito.never())
                .toQuestionDto(question);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        questionService.delete(given);
        Mockito.verify(questionRepository, Mockito.times(1))
                .deleteById(given);
    }

    @Test
    void getRandomQuestion_Success() {
        Integer givenSubjectId = 1;
        Integer givenCount = 10;
        List<Question> questionList = new ArrayList<>();
        List<QuestionWithoutAnswerResponseDto> expected = new ArrayList<>();

        Mockito.when(questionRepository.getRandomQuestion(givenSubjectId, givenCount))
                .thenReturn(questionList);
        Mockito.when(questionMapper.toQuestionWithoutAnswerDtoList(questionList))
                .thenReturn(expected);

        List<QuestionWithoutAnswerResponseDto> actual =
                questionService.getRandomQuestions(givenSubjectId,givenCount);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(questionRepository, Mockito.times(1))
                .getRandomQuestion(givenSubjectId, givenCount);
        Mockito.verify(questionMapper, Mockito.times(1))
                .toQuestionWithoutAnswerDtoList(questionList);
    }

    @Test
    void findByIdList_Success() {
        List<Integer> givenIdList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        List<QuestionResponseDto> expected = new ArrayList<>();

        Mockito.when(questionRepository.findByIdIn(givenIdList))
                .thenReturn(questionList);
        Mockito.when(questionMapper.toQuestionDtoList(questionList))
                .thenReturn(expected);

        List<QuestionResponseDto> actual =
                questionService.getByIdList(givenIdList);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(questionRepository, Mockito.times(1))
                .findByIdIn(givenIdList);
        Mockito.verify(questionMapper, Mockito.times(1))
                .toQuestionDtoList(questionList);
    }
}
