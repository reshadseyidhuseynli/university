package com.company.controller;

import com.company.dto.request.ExamAnswerRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<QuestionWithoutAnswerResponseDto>> getExaminationPaperByTeacherId(
            @RequestParam(name = "teacherId") Integer id){
        return ResponseEntity.ok(examService.getExaminationPaperByTeacherId(id));
    }

    @PostMapping
    public ResponseEntity<ExamResultResponseDto> acceptAnswersAndGiveResult(
            @RequestBody ExamAnswerRequestDto requestDto){
        return ResponseEntity.ok(examService.acceptAnswersAndGiveResult(requestDto));
    }
}
