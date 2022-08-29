package com.company.controller;

import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.TeacherResponseDto;
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
    public ResponseEntity<List<TeacherResponseDto>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentResponseDto>> getTeacherStudents(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.getStudentsOfTeacherById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherResponseDto> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(teacherService.delete(id));
    }

}
