package com.company.controller;

import com.company.dto.QuestionDTO;
import com.company.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> getAll() {
        final List<QuestionDTO> all = questionService.getAll();

        return ResponseEntity.ok(ResponseDTO.create(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        final QuestionDTO byId = questionService.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        final QuestionDTO questionDTO = questionService.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(questionDTO, "The question successfully deleted"));
    }
}
