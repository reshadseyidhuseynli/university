package com.company.service;

import com.company.dto.QuestionDto;
import com.company.entity.Question;
import com.company.error.ServiceException;
import com.company.mapper.QuestionMapper;
import com.company.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    QuestionService(QuestionRepository questionRepository,
                    QuestionMapper questionMapper) {

        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public List<QuestionDto> getAll() {

        return questionMapper.toQuestionDtoList(questionRepository.findAll());
    }

    public QuestionDto getById(Integer id) {

        return questionMapper.toQuestionDto(questionRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Question not found, by id: " + id)));
    }

    public QuestionDto delete(Integer id) {

        final Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Question not found, by id: " + id));

        questionRepository.delete(question);

        return questionMapper.toQuestionDto(question);

    }

}
