package com.company.service;

import com.company.dto.response.ExamResultResponseDto;
import com.company.entity.ExamResult;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.repository.ExamResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamResultService {

    private final ExamResultRepository examResultRepository;
    private final ExamResultMapper examResultMapper;

    public List<ExamResultResponseDto> getAll() {
        return examResultMapper.toExamResultDtoList(examResultRepository.findAll());
    }

    public ExamResultResponseDto getById(Integer id) {
        return examResultMapper.toExamResultDto(findByIdIfAvailable(id));
    }

    public ExamResultResponseDto delete(Integer id) {
        final ExamResult examResult = findByIdIfAvailable(id);
        examResultRepository.delete(examResult);

        return examResultMapper.toExamResultDto(examResult);
    }

    private ExamResult findByIdIfAvailable(Integer id){
        return examResultRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.EXAM_RESULT_NOT_FOUND,
                        "ExamResult not found, by id: " + id));
    }

}
