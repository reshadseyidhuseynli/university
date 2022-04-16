package com.company.restful;

import com.company.database.entity.Question;
import com.company.database.service.inter.QuestionService;
import com.company.dto.QuestionDTO;
import com.company.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping
    @RequestMapping("/questions")
    public ResponseEntity<ResponseDTO> getAll(){
        List<QuestionDTO> response = new ArrayList<>();
        for (Question q : service.getAll()){
            response.add(new QuestionDTO(q));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/questions/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id){
        QuestionDTO response = new QuestionDTO(service.getById(id));
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @DeleteMapping
    @RequestMapping("/questions/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Question question = service.getById(id);
        service.delete(question);
        QuestionDTO response = new QuestionDTO(question);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }
}
