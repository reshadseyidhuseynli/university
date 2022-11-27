package com.company.controller;

import com.company.dto.response.ExamResultResponseDto;
import com.company.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/results")
public class ExamResultController {

    private final ExamResultService examResultService;

    @GetMapping
    public List<ExamResultResponseDto> getAll() {
        return examResultService.getAll();
    }

    @GetMapping("/{id}")
    public ExamResultResponseDto getById(@PathVariable Integer id) {
        return examResultService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        examResultService.delete(id);
    }
}
