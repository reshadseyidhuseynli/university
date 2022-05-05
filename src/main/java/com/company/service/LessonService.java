package com.company.service;

import com.company.dto.LessonDto;
import com.company.dto.QuestionDto;
import com.company.entity.Lesson;
import com.company.entity.Question;
import com.company.error.ServiceException;
import com.company.mapper.LessonMapper;
import com.company.mapper.QuestionMapper;
import com.company.repository.LessonRepository;
import com.company.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonService {

    private final LessonRepository lessonRepository;
    private final QuestionRepository questionRepository;
    private final LessonMapper lessonMapper;
    private final QuestionMapper questionMapper;

    LessonService(LessonRepository lessonRepository,
                  QuestionRepository questionRepository,
                  LessonMapper lessonMapper,
                  QuestionMapper questionMapper) {

        this.lessonRepository = lessonRepository;
        this.questionRepository = questionRepository;
        this.lessonMapper = lessonMapper;
        this.questionMapper = questionMapper;
    }

    public List<LessonDto> getAll() {

        return lessonMapper.toLessonDtoList(lessonRepository.findAll());
    }

    public LessonDto getById(Integer id) {

        return lessonMapper.toLessonDto(lessonRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Lesson not found, by id: " + id)));
    }

    public LessonDto delete(Integer id) {

        final Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Lesson not found, by id: " + id));

        lessonRepository.delete(lesson);

        return lessonMapper.toLessonDto(lesson);

    }

    public QuestionDto addQuestionToLesson(Integer lessonId,
                                           String text,
                                           String option1,
                                           String option2,
                                           String option3,
                                           String option4,
                                           Integer trueOption) {

        final Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ServiceException("Lesson not found, by id: " + lessonId));

        final Question question = new Question(text, option1, option2, option3, option4, trueOption);
        question.setLesson(lesson);

        return questionMapper.toQuestionDto(questionRepository.save(question));

    }

}
