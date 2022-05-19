package com.company.controller;

import com.company.dto.response.QuestionWithAnswerResponseDto;
import com.company.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<QuestionWithAnswerResponseDto>> getAll() {
        return ResponseEntity.ok(questionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionWithAnswerResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionWithAnswerResponseDto> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(questionService.delete(id));
    }

}
