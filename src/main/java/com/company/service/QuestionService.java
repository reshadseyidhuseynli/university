package com.company.service;

import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
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
        return questionMapper.toQuestionDto(findById(id));
    }

    public void delete(Integer id) {
        questionRepository.deleteById(id);
    }

    public List<QuestionWithoutAnswerResponseDto> getRandomQuestions(Integer subjectId, Integer count) {
        return questionMapper.toQuestionWithoutAnswerDtoList(
                questionRepository.getRandomQuestion(subjectId, count));
    }

    public List<QuestionResponseDto> getByIdList(List<Integer> ids) {
        return questionMapper.toQuestionDtoList(questionRepository.findByIdIn(ids));
    }

    private Question findById(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.QUESTION_NOT_FOUND,
                        "Question not found, by id: " + id));
    }

}
