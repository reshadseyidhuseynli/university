package com.company.restful;

import com.company.database.entity.Student;
import com.company.database.service.inter.StudentService;
import com.company.dto.ResponseDTO;
import com.company.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    @RequestMapping("/students")
    public ResponseEntity<ResponseDTO> getAll(){
        List<StudentDTO> response = new ArrayList<>();
        for (Student s : service.getAll()){
            response.add(new StudentDTO(s));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/students/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id){
        StudentDTO response = new StudentDTO(service.getById(id));
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @DeleteMapping
    @RequestMapping("/students/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Student student = service.getById(id);
        service.delete(student);
        StudentDTO response = new StudentDTO(student);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }
}
