package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.StudentDTO;
import com.company.dto.TeacherDTO;
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
    public ResponseEntity<ResponseDTO> getAll() {
        final List<TeacherDTO> all = teacherService.getAll();

        return ResponseEntity.ok(ResponseDTO.create(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        final TeacherDTO byId = teacherService.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<ResponseDTO> getTeacherStudent(@PathVariable("id") Integer id) {
        final List<StudentDTO> students = teacherService.getById(id).getStudents();

        return ResponseEntity.ok(ResponseDTO.create(students));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        final TeacherDTO teacherDTO = teacherService.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(teacherDTO, "The teacher successfully deleted"));
    }
}
