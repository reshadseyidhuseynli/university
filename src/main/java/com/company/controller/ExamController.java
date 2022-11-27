package com.company.controller;

import com.company.dto.request.ExamAnswerRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public List<QuestionWithoutAnswerResponseDto> getExaminationPaperByTeacherId(
            @RequestParam(name = "subject-id") Integer subjectId){
        return examService.getExaminationPaperBySubjectId(subjectId);
    }

    @PostMapping
    public ExamResultResponseDto acceptAnswersAndGiveResult(
            @RequestBody ExamAnswerRequestDto requestDto){
        return examService.acceptAnswersAndGiveResult(requestDto);
    }
}
