package com.company.controller;

import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<QuestionResponseDto> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/{id}")
    public QuestionResponseDto getById(@PathVariable Integer id) {
        return questionService.getById(id);
    }

    @GetMapping("/random/{subjectId}/{count}")
    public List<QuestionWithoutAnswerResponseDto> getRandomQuestions(@PathVariable Integer subjectId,
                                                                     @PathVariable Integer count) {
        return questionService.getRandomQuestions(subjectId, count);
    }

    @GetMapping("/list")
    public List<QuestionResponseDto> getByIdList(@RequestBody List<Integer> ids) {
        return questionService.getByIdList(ids);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        questionService.delete(id);
    }

}
