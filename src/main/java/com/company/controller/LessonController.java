package com.company.controller;

import com.company.dto.LessonDTO;
import com.company.dto.QuestionDTO;
import com.company.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> getAll() {
        final List<LessonDTO> all = lessonService.getAll();

        return ResponseEntity.ok(ResponseDTO.create(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        final LessonDTO byId = lessonService.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<ResponseDTO> getLessonQuestions(@PathVariable("id") Integer id) {
        final List<QuestionDTO> questions = lessonService.getById(id).getQuestions();

        return ResponseEntity.ok(ResponseDTO.create(questions));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        final LessonDTO lessonDTO = lessonService.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(lessonDTO, "The lesson successfully deleted"));
    }


    @PostMapping("/{id}/add+question")
    public ResponseEntity<ResponseDTO> addQuestion(@PathVariable("id") Integer lessonId,
                                                   @RequestParam(value = "text") String text,
                                                   @RequestParam(value = "option1") String option1,
                                                   @RequestParam(value = "option2") String option2,
                                                   @RequestParam(value = "option3") String option3,
                                                   @RequestParam(value = "option4") String option4,
                                                   @RequestParam(value = "trueOption") Integer trueOption) {
        final QuestionDTO questionDTO = lessonService.addQuestionToLesson(lessonId, text, option1, option2, option3, option4, trueOption);

        return ResponseEntity.ok(ResponseDTO.create(questionDTO, "successfully added"));
    }
}
