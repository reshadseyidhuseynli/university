package com.company.service;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.entity.Question;
import com.company.entity.Subject;
import com.company.error.ServiceException;
import com.company.mapper.QuestionMapper;
import com.company.mapper.SubjectMapper;
import com.company.repository.QuestionRepository;
import com.company.repository.SubjectRepository;
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
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private SubjectMapper subjectMapper;
    @Mock
    private QuestionMapper questionMapper;
    @InjectMocks
    private SubjectService subjectService;

    @Test
    void getAllTest() {
        List<Subject> subjectList = new ArrayList<>();
        List<SubjectResponseDto> expected = new ArrayList<>();

        Mockito.when(subjectRepository.findAll())
                .thenReturn(subjectList);
        Mockito.when(subjectMapper.toSubjectDtoList(subjectList))
                .thenReturn(expected);

        List<SubjectResponseDto> actual = subjectService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(subjectRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(subjectMapper, Mockito.times(1))
                .toSubjectDtoList(subjectList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;
        Subject subject = new Subject();
        SubjectResponseDto expected = new SubjectResponseDto();

        Mockito.when(subjectRepository.findById(given))
                .thenReturn(Optional.of(subject));
        Mockito.when(subjectMapper.toSubjectDto(subject))
                .thenReturn(expected);

        SubjectResponseDto actual = subjectService.getById(given);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(subjectRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(subjectMapper, Mockito.times(1))
                .toSubjectDto(subject);
    }

    @Test
    void getByIdNotFoundTest() {
        Integer given = 100;
        Subject subject = new Subject();

        Mockito.when(subjectRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> subjectService.getById(given));
        Mockito.verify(subjectRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(subjectMapper, Mockito.never())
                .toSubjectDto(subject);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        subjectService.delete(given);
        Mockito.verify(subjectRepository, Mockito.times(1))
                .deleteById(given);
    }

    @Test
    void addQuestionToLessonTest() {
        Integer givenId = 1;
        QuestionRequestDto givenDto = new QuestionRequestDto();

        Question question = new Question();
        Subject subject = new Subject();
        QuestionResponseDto expected = new QuestionResponseDto();

        Mockito.when(subjectRepository.findById(givenId))
                .thenReturn(Optional.of(subject));
        Mockito.when(questionMapper.toQuestion(givenDto))
                .thenReturn(question);
        Mockito.when(questionRepository.save(question))
                .thenReturn(question);
        Mockito.when(questionMapper.toQuestionDto(question))
                .thenReturn(expected);

        QuestionResponseDto actual = subjectService.addQuestionToLesson(givenId, givenDto);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(subjectRepository, Mockito.times(1))
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

        Mockito.when(subjectRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class,
                () -> subjectService.addQuestionToLesson(given, givenDto));
        Mockito.verify(subjectRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(questionMapper, Mockito.never())
                .toQuestion(givenDto);
        Mockito.verify(questionMapper, Mockito.never())
                .toQuestionDto(question);
    }
}
