package com.company.controller;

import com.company.dto.ExamResultDto;
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
    public ResponseEntity<List<ExamResultDto>> getAll() {

        return ResponseEntity.ok(examResultService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResultDto> getById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(examResultService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExamResultDto> delete(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(examResultService.delete(id));
    }
}
