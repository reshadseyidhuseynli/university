package com.company.controller;

import com.company.dto.ExamResultDTO;
import com.company.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> getAll() {
        final List<ExamResultDTO> all = examResultService.getAll();

        return ResponseEntity.ok(ResponseDTO.create(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        final ExamResultDTO byId = examResultService.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        final ExamResultDTO examResultDTO = examResultService.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(examResultDTO, "The Result successfully deleted"));
    }
}
