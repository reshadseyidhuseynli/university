package com.company.service;

import com.company.dto.response.ExamResultResponseDto;
import com.company.entity.ExamResult;
import com.company.mapper.ExamResultMapper;
import com.company.repository.ExamResultRepository;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
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
        return examResultMapper.toExamResultDto(findById(id));
    }

    public void delete(Integer id) {
        examResultRepository.deleteById(id);
    }

    private ExamResult findById(Integer id){
        return examResultRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.EXAM_RESULT_NOT_FOUND,
                        "ExamResult not found, by id: " + id));
    }

}
