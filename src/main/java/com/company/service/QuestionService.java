package com.company.service;

import com.company.dto.response.QuestionResponseDto;
import com.company.entity.Question;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.QuestionMapper;
import com.company.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionResponseDto> getAll() {
        return questionMapper.toQuestionDtoList(questionRepository.findAll());
    }

    public QuestionResponseDto getById(Integer id) {
        return questionMapper.toQuestionDto(findByIdIfAvailable(id));
    }

    public QuestionResponseDto delete(Integer id) {
        final Question question = findByIdIfAvailable(id);
        questionRepository.delete(question);

        return questionMapper.toQuestionDto(question);
    }

    private Question findByIdIfAvailable(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.QUESTION_NOT_FOUND,
                        "Question not found, by id: " + id));
    }

}
