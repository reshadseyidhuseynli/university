package com.company.controller;

import com.company.dto.StudentDto;
import com.company.dto.TeacherDto;
import com.company.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {

        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(teacherService.getById(id));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDto>> getTeacherStudent(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(teacherService.getById(id).getStudents());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDto> delete(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(teacherService.delete(id));
    }

}
