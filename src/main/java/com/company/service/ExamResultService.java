package com.company.service;

import com.company.dto.ExamResultDTO;
import com.company.entity.ExamResult;
import com.company.repository.ExamResultRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamResultService {

    private final ExamResultRepository examResultRepository;

    ExamResultService(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    public List<ExamResultDTO> getAll() {
        final List<ExamResult> all = examResultRepository.findAll();

        List<ExamResultDTO> examResultDTOS = new ArrayList<>();
        for (ExamResult examResult : all) {
            examResultDTOS.add(new ExamResultDTO(examResult));
        }

        return examResultDTOS;
    }

    public ExamResultDTO getById(Integer id) {
        final ExamResult byId = examResultRepository.getById(id);

        return new ExamResultDTO(byId);
    }

    public ExamResultDTO delete(Integer id) {
        final ExamResult byId = examResultRepository.getById(id);
        examResultRepository.delete(byId);

        return new ExamResultDTO(byId);
    }
}
