package com.company.service;

import com.company.dto.LessonDTO;
import com.company.dto.QuestionDTO;
import com.company.entity.Lesson;
import com.company.entity.Question;
import com.company.error.ServiceException;
import com.company.repository.LessonRepository;
import com.company.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LessonService {

    private final LessonRepository lessonRepository;
    private final QuestionRepository questionRepository;

    LessonService(LessonRepository lessonRepository,
                  QuestionRepository questionRepository) {
        this.lessonRepository = lessonRepository;
        this.questionRepository = questionRepository;
    }

    public List<LessonDTO> getAll() {
        final List<Lesson> all = lessonRepository.findAll();

        List<LessonDTO> lessonDTOS = new ArrayList<>();
        for (Lesson lesson : all) {
            lessonDTOS.add(new LessonDTO(lesson));
        }

        return lessonDTOS;
    }

    public LessonDTO getById(Integer id) {
        final Lesson byId = lessonRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Lesson not found, by id: " + id));

        return new LessonDTO(byId);
    }

    public LessonDTO delete(Integer id) {
        final Lesson byId = lessonRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Lesson not found, by id: " + id));
        lessonRepository.delete(byId);

        return new LessonDTO(byId);
    }

    public QuestionDTO addQuestionToLesson(Integer lessonId,
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
        questionRepository.save(question);

        return new QuestionDTO(question);
    }
}
