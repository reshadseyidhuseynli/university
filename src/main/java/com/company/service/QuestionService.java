package com.company.service;

import com.company.dto.QuestionDTO;
import com.company.entity.Question;
import com.company.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> getAll() {
        final List<Question> all = questionRepository.findAll();

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : all) {
            questionDTOS.add(new QuestionDTO(question));
        }

        return questionDTOS;
    }

    public QuestionDTO getById(Integer id) {
        final Question byId = questionRepository.getById(id);

        return new QuestionDTO(byId);
    }

    public QuestionDTO delete(Integer id) {
        final Question byId = questionRepository.getById(id);
        questionRepository.delete(byId);

        return new QuestionDTO(byId);
    }
}
