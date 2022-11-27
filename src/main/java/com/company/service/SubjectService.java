package com.company.service;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.entity.Question;
import com.company.entity.Subject;
import com.company.repository.QuestionRepository;
import com.company.repository.SubjectRepository;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.SubjectMapper;
import com.company.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final QuestionRepository questionRepository;
    private final SubjectMapper subjectMapper;
    private final QuestionMapper questionMapper;

    public List<SubjectResponseDto> getAll() {
        return subjectMapper.toSubjectDtoList(subjectRepository.findAll());
    }

    public SubjectResponseDto getById(Integer id) {
        return subjectMapper.toSubjectDto(findById(id));
    }

    public void delete(Integer id) {
        subjectRepository.deleteById(id);
    }

    public QuestionResponseDto addQuestionToLesson(Integer lessonId,
                                                   QuestionRequestDto requestDto) {
        Subject subject = findById(lessonId);

        Question question = questionMapper.toQuestion(requestDto);
        question.setSubject(subject);

        return questionMapper.toQuestionDto(questionRepository.save(question));
    }

    private Subject findById(Integer id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.SUBJECT_NOT_FOUND,
                        "Lesson not found, by id: " + id));
    }

}
