package com.company.controller;

import com.company.dto.ExamResultDTO;
import com.company.dto.ResponseDTO;
import com.company.dto.StudentDTO;
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
    public ResponseEntity<ResponseDTO> getAll() {
        final List<StudentDTO> all = studentService.getAll();

        return ResponseEntity.ok(ResponseDTO.create(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        final StudentDTO byId = studentService.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<ResponseDTO> getStudentResults(@PathVariable("id") Integer id) {
        final List<ExamResultDTO> examResults = studentService.getById(id).getExamResults();

        return ResponseEntity.ok(ResponseDTO.create(examResults));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        final StudentDTO studentDTO = studentService.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(studentDTO, "The student successfully deleted"));
    }

    @PostMapping("/{id}/result")
    public ResponseEntity<ResponseDTO> add(@PathVariable("id") Integer studentId,
                                           @RequestParam("teacherId") Integer teacherId,
                                           @RequestParam("trueAnswerCount") Integer trueAnswerCount,
                                           @RequestParam("falseAnswerCount") Integer falseAnswerCount) {
        final ExamResultDTO examResultDTO = studentService.addExamResultToStudent(studentId,teacherId,trueAnswerCount, falseAnswerCount);

        return ResponseEntity.ok(ResponseDTO.create(examResultDTO, "The result successfully added"));
    }
}
