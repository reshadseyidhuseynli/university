package com.company.controller;

import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherResponseDto> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public TeacherResponseDto getById(@PathVariable Integer id) {
        return teacherService.getById(id);
    }

    @GetMapping("/{id}/students")
    public List<StudentResponseDto> getTeacherStudents(@PathVariable Integer id) {
        return teacherService.getStudentsOfTeacherById(id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        teacherService.delete(id);
    }

}
