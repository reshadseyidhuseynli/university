package com.company.service;

import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.QuestionWithAnswerResponseDto;
import com.company.dto.request.QuestionRequestDto;
import com.company.entity.Lesson;
import com.company.entity.Question;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.LessonMapper;
import com.company.mapper.QuestionWithAnswerMapper;
import com.company.repository.LessonRepository;
import com.company.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final QuestionRepository questionRepository;
    private final LessonMapper lessonMapper;
    private final QuestionWithAnswerMapper questionWithAnswerMapper;

    public List<LessonResponseDto> getAll() {
        return lessonMapper.toLessonDtoList(lessonRepository.findAll());
    }

    public LessonResponseDto getById(Integer id) {
        return lessonMapper.toLessonDto(findByIdIfAvailable(id));
    }

    public LessonResponseDto delete(Integer id) {
        final Lesson lesson = findByIdIfAvailable(id);
        lessonRepository.delete(lesson);

        return lessonMapper.toLessonDto(lesson);
    }

    public QuestionWithAnswerResponseDto addQuestionToLesson(Integer lessonId,
                                                             QuestionRequestDto requestDto) {
        final Lesson lesson = findByIdIfAvailable(lessonId);

        final Question question = new Question(
                requestDto.getText(),
                requestDto.getOption1(),
                requestDto.getOption2(),
                requestDto.getOption3(),
                requestDto.getOption4(),
                requestDto.getTrueOption());
        question.setLesson(lesson);

        return questionWithAnswerMapper.toQuestionWithAnswerDto(questionRepository.save(question));
    }

    private Lesson findByIdIfAvailable(Integer id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.LESSON_NOT_FOUND,
                        "Lesson not found, by id: " + id));
    }

}
