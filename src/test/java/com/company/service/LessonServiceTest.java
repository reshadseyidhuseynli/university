package com.company.service;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.entity.Lesson;
import com.company.entity.Question;
import com.company.error.ServiceException;
import com.company.mapper.LessonMapper;
import com.company.mapper.QuestionMapper;
import com.company.repository.LessonRepository;
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
class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private LessonMapper lessonMapper;
    @Mock
    private QuestionMapper questionMapper;
    @InjectMocks
    private LessonService lessonService;

    @Test
    void getAllTest() {
        List<Lesson> lessonList = new ArrayList<>();
        List<LessonResponseDto> expected = new ArrayList<>();

        Mockito.when(lessonRepository.findAll())
                .thenReturn(lessonList);
        Mockito.when(lessonMapper.toLessonDtoList(lessonList))
                .thenReturn(expected);

        List<LessonResponseDto> actual = lessonService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(lessonRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(lessonMapper, Mockito.times(1))
                .toLessonDtoList(lessonList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;
        Lesson lesson = new Lesson();
        LessonResponseDto expected = new LessonResponseDto();

        Mockito.when(lessonRepository.findById(given))
                .thenReturn(Optional.of(lesson));
        Mockito.when(lessonMapper.toLessonDto(lesson))
                .thenReturn(expected);

        LessonResponseDto actual = lessonService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(lessonRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(lessonMapper, Mockito.times(1))
                .toLessonDto(lesson);
    }

    @Test
    void getByIdNotFoundTest() {
        Integer given = 100;
        Lesson lesson = new Lesson();

        Mockito.when(lessonRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> lessonService.getById(given));
        Mockito.verify(lessonRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(lessonMapper, Mockito.never())
                .toLessonDto(lesson);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        Lesson lesson = new Lesson();
        LessonResponseDto expected = new LessonResponseDto();

        Mockito.when(lessonRepository.findById(given))
                .thenReturn(Optional.of(lesson));
        Mockito.when(lessonMapper.toLessonDto(lesson))
                .thenReturn(expected);

        LessonResponseDto actual = lessonService.delete(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(lessonRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(lessonRepository, Mockito.times(1))
                .delete(lesson);
        Mockito.verify(lessonMapper, Mockito.times(1))
                .toLessonDto(lesson);
    }

    @Test
    void deleteNotFoundTest() {
        Integer given = 100;
        Lesson lesson = new Lesson();

        Mockito.when(lessonRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> lessonService.delete(given));
        Mockito.verify(lessonRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(lessonRepository, Mockito.never())
                .delete(lesson);
        Mockito.verify(lessonMapper, Mockito.never())
                .toLessonDto(lesson);
    }

    @Test
    void addQuestionToLessonTest() {
        Integer givenId = 1;
        QuestionRequestDto givenDto = new QuestionRequestDto();

        Question question = new Question();
        Lesson lesson = new Lesson();
        QuestionResponseDto expected = new QuestionResponseDto();

        Mockito.when(lessonRepository.findById(givenId))
                .thenReturn(Optional.of(lesson));
        Mockito.when(questionMapper.toQuestion(givenDto))
                .thenReturn(question);
        Mockito.when(questionRepository.save(question))
                .thenReturn(question);
        Mockito.when(questionMapper.toQuestionDto(question))
                .thenReturn(expected);

        QuestionResponseDto actual = lessonService.addQuestionToLesson(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(lessonRepository, Mockito.times(1))
                .findById(givenId);
        Mockito.verify(questionMapper, Mockito.times(1))
                .toQuestion(givenDto);
        Mockito.verify(questionRepository, Mockito.times(1))
                .save(question);
        Mockito.verify(questionMapper, Mockito.times(1))
                .toQuestionDto(question);
    }

    @Test
    void addQuestionToLessonNotFoundTest() {
        Integer given = 100;
        QuestionRequestDto givenDto = new QuestionRequestDto();
        Question question = new Question();

        Mockito.when(lessonRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> lessonService.delete(given));
        Mockito.verify(lessonRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(questionMapper, Mockito.never())
                .toQuestion(givenDto);
        Mockito.verify(questionMapper, Mockito.never())
                .toQuestionDto(question);
    }
}
