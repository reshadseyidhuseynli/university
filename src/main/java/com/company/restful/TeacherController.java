package com.company.restful;

import com.company.database.entity.JoinTeacherStudent;
import com.company.database.entity.Teacher;
import com.company.database.service.inter.TeacherService;
import com.company.dto.ResponseDTO;
import com.company.dto.StudentDTO;
import com.company.dto.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    @RequestMapping("/teachers")
    public ResponseEntity<ResponseDTO> getAll(){
        List<TeacherDTO> response = new ArrayList<>();
        for (Teacher t : service.getAll()){
            response.add(new TeacherDTO(t));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/teachers/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id){
        TeacherDTO response = new TeacherDTO(service.getById(id));
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/teachers/{id}/students")
    public ResponseEntity<ResponseDTO> getTheTeacherStudent(@PathVariable("id") Integer id){
        List<StudentDTO> response = new ArrayList<>();
        for (JoinTeacherStudent j : service.getById(id).getStudents()){
            response.add(new StudentDTO(j.getStudent()));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }


    @DeleteMapping
    @RequestMapping("/teachers/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Teacher teacher = service.getById(id);
        service.delete(teacher);
        TeacherDTO response = new TeacherDTO(teacher);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }
}
