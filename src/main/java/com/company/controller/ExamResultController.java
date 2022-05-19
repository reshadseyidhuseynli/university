package com.company.controller;

import com.company.dto.response.ExamResultResponseDto;
import com.company.service.ExamResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ExamResultController {

    private final ExamResultService examResultService;

    ExamResultController(ExamResultService service) {
        this.examResultService = service;
    }

    @GetMapping
    public ResponseEntity<List<ExamResultResponseDto>> getAll() {
        return ResponseEntity.ok(examResultService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResultResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(examResultService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExamResultResponseDto> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(examResultService.delete(id));
    }
}
