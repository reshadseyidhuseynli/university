package com.company.service;

import com.company.dto.ExamResultDto;
import com.company.entity.ExamResult;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.repository.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final ExamResultMapper examResultMapper;

    ExamResultService(ExamResultRepository examResultRepository,
                      ExamResultMapper examResultMapper) {

        this.examResultRepository = examResultRepository;
        this.examResultMapper = examResultMapper;

    }

    public List<ExamResultDto> getAll() {

        return examResultMapper.toExamResultDtoList(examResultRepository.findAll());
    }

    public ExamResultDto getById(Integer id) {

        return examResultMapper.toExamResultDto(examResultRepository.findById(id)
                .orElseThrow(() -> new ServiceException("ExamResult not found, by id: " + id)));
    }

    public ExamResultDto delete(Integer id) {

        final ExamResult examResult = examResultRepository.findById(id)
                .orElseThrow(() -> new ServiceException("ExamResult not found, by id: " + id));

        examResultRepository.delete(examResult);

        return examResultMapper.toExamResultDto(examResult);

    }

}
