package com.company.restful;

import com.company.database.entity.Faculty;
import com.company.database.entity.Lesson;
import com.company.database.entity.Question;
import com.company.database.service.inter.LessonService;
import com.company.dto.LessonDTO;
import com.company.dto.QuestionDTO;
import com.company.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LessonController {

    @Autowired
    private LessonService service;

    @GetMapping
    @RequestMapping("/lessons")
    public ResponseEntity<ResponseDTO> getAll(){
        List<LessonDTO> response = new ArrayList<>();
        for (Lesson l : service.getAll()){
            response.add(new LessonDTO(l));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/lessons/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id){
        LessonDTO response = new LessonDTO(service.getById(id));
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/lessons/{id}/questions")
    public ResponseEntity<ResponseDTO> getTheLessonQuestions(@PathVariable("id") Integer id){
        List<QuestionDTO> response = new ArrayList<>();
        for (Question q : service.getById(id).getQuestions()){
            response.add(new QuestionDTO(q));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @DeleteMapping
    @RequestMapping("/lessons/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Lesson lesson = service.getById(id);
        service.delete(lesson);
        LessonDTO response = new LessonDTO(lesson);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }


    @GetMapping
    @RequestMapping("/lessons/{id}/add+question")
    public ResponseEntity<ResponseDTO> addQuestion(@PathVariable("id") Integer id,
                                                   @RequestParam(value = "text") String text,
                                                   @RequestParam(value = "option1") String option1,
                                                   @RequestParam(value = "option2") String option2,
                                                   @RequestParam(value = "option3") String option3,
                                                   @RequestParam(value = "option4") String option4,
                                                   @RequestParam(value = "trueOption") Integer trueOption){
        Lesson lesson = service.getById(id);
        Question newQuestion = new Question(text, option1, option2, option3, option4, trueOption);
        lesson.getQuestions().add(newQuestion);
        service.addOrUpdate(lesson);
        QuestionDTO response = new QuestionDTO(newQuestion);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully added"));
    }
}
