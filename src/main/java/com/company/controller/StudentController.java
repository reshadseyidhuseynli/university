package com.company.controller;

import com.company.dto.ExamResultDto;
import com.company.dto.StudentDto;
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
    public ResponseEntity<List<StudentDto>> getAll() {

        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<List<ExamResultDto>> getStudentResults(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(studentService.getById(id).getExamResults());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> delete(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(studentService.delete(id));
    }

    @PostMapping("/{id}/result")
    public ResponseEntity<ExamResultDto> add(@PathVariable("id") Integer studentId,
                                             @RequestParam("teacherId") Integer teacherId,
                                             @RequestParam("trueAnswerCount") Integer trueAnswerCount,
                                             @RequestParam("falseAnswerCount") Integer falseAnswerCount) {

        return ResponseEntity.ok(studentService.addExamResultToStudent(
                studentId,
                teacherId,
                trueAnswerCount,
                falseAnswerCount));
    }

}
