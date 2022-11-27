package com.company.controller;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponseDto> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getById(@PathVariable Integer id) {
        return studentService.getById(id);
    }

    @GetMapping("/{id}/results")
    public List<ExamResultResponseDto> getStudentResultsById(@PathVariable Integer id) {
        return studentService.getById(id).getExamResults();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.delete(id);
    }

    @PostMapping("/{id}/result")
    public ExamResultResponseDto addExamResult(@PathVariable("id") Integer studentId,
                                               @RequestBody ExamResultRequestDto requestDto) {
        return studentService.addExamResultForStudent(studentId, requestDto);
    }

}
