package com.company.restful;

import com.company.database.entity.Lesson;
import com.company.database.service.inter.LessonService;
import com.company.dto.LessonDTO;
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

    @DeleteMapping
    @RequestMapping("/lessons/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Lesson lesson = service.getById(id);
        service.delete(lesson);
        LessonDTO response = new LessonDTO(lesson);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }
}
