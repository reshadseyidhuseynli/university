package com.company.controller;

import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.QuestionWithAnswerResponseDto;
import com.company.dto.request.QuestionRequestDto;
import com.company.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<List<LessonResponseDto>> getAll() {
        return ResponseEntity.ok(lessonService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.getById(id));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionWithAnswerResponseDto>> getLessonQuestions(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.getById(id).getQuestions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LessonResponseDto> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(lessonService.delete(id));
    }


    @PostMapping("/{id}/question")
    public ResponseEntity<QuestionWithAnswerResponseDto> addQuestion(@PathVariable("id") Integer lessonId,
                                                                     @RequestBody QuestionRequestDto requestDto) {
        return ResponseEntity.ok(lessonService.addQuestionToLesson(
                lessonId,
                requestDto));
    }

}
