package com.company.controller;

import com.company.dto.LessonDto;
import com.company.dto.QuestionDto;
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
    public ResponseEntity<List<LessonDto>> getAll() {

        return ResponseEntity.ok(lessonService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(lessonService.getById(id));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionDto>> getLessonQuestions(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(lessonService.getById(id).getQuestions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LessonDto> delete(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(lessonService.delete(id));
    }


    @PostMapping("/{id}/add+question")
    public ResponseEntity<QuestionDto> addQuestion(@PathVariable("id") Integer lessonId,
                                                   @RequestParam(value = "text") String text,
                                                   @RequestParam(value = "option1") String option1,
                                                   @RequestParam(value = "option2") String option2,
                                                   @RequestParam(value = "option3") String option3,
                                                   @RequestParam(value = "option4") String option4,
                                                   @RequestParam(value = "trueOption") Integer trueOption) {

        return ResponseEntity.ok(lessonService.addQuestionToLesson(
                lessonId,
                text,
                option1,
                option2,
                option3,
                option4,
                trueOption));

    }

}
