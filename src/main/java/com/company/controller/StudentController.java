package com.company.controller;

import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.request.ExamResultRequestDto;
import com.company.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<List<ExamResultResponseDto>> getStudentResults(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getById(id).getExamResults());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDto> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @PostMapping("/{id}/result")
    public ResponseEntity<ExamResultResponseDto> add(@PathVariable("id") Integer studentId,
                                                     @RequestBody ExamResultRequestDto requestDto) {
        return ResponseEntity.ok(studentService.addExamResultToStudent(
                studentId,
                requestDto));
    }

}
