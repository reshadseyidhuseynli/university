package com.company.service;

import com.company.dto.response.QuestionWithAnswerResponseDto;
import com.company.entity.Question;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.QuestionWithAnswerMapper;
import com.company.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionWithAnswerMapper questionWithAnswerMapper;

    QuestionService(QuestionRepository questionRepository,
                    QuestionWithAnswerMapper questionWithAnswerMapper) {

        this.questionRepository = questionRepository;
        this.questionWithAnswerMapper = questionWithAnswerMapper;
    }

    public List<QuestionWithAnswerResponseDto> getAll() {
        return questionWithAnswerMapper.toQuestionWithAnswerDtoList(questionRepository.findAll());
    }

    public QuestionWithAnswerResponseDto getById(Integer id) {
        return questionWithAnswerMapper.toQuestionWithAnswerDto(questionRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.QUESTION_NOT_FOUND,
                        "Question not found, by id: " + id)));
    }

    public QuestionWithAnswerResponseDto delete(Integer id) {

        final Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.QUESTION_NOT_FOUND,
                        "Question not found, by id: " + id));

        questionRepository.delete(question);

        return questionWithAnswerMapper.toQuestionWithAnswerDto(question);

    }

}
