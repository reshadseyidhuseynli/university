package com.company.controller;

import com.company.dto.request.FacultyRequestDto;
import com.company.dto.request.LessonRequestDto;
import com.company.dto.request.StudentRequestDto;
import com.company.dto.request.TeacherRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<FacultyResponseDto>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.getById(id));
    }

    @GetMapping("/{id}/head")
    public ResponseEntity<TeacherResponseDto> getFacultyHead(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.getById(id).getHead());
    }

    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonResponseDto>> getFacultyLessons(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.getById(id).getLessons());
    }

    @GetMapping("/{id}/teachers")
    public ResponseEntity<List<TeacherResponseDto>> getFacultyTeachers(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.getById(id).getTeachers());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentResponseDto>> getFacultyStudents(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.getById(id).getStudents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacultyResponseDto> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(facultyService.delete(id));
    }

    @PostMapping
    public ResponseEntity<FacultyResponseDto> add(@RequestBody FacultyRequestDto requestDto) {
        return ResponseEntity.ok(facultyService.add(requestDto));
    }

    @PostMapping("/{id}/lesson")
    public ResponseEntity<LessonResponseDto> addLesson(@PathVariable("id") Integer facultyId,
                                                       @RequestBody LessonRequestDto requestDto) {

        return ResponseEntity.ok(facultyService.addLessonToFaculty(
                facultyId,
                requestDto));
    }

    @PostMapping("/{id}/teacher")
    public ResponseEntity<TeacherResponseDto> addTeacher(@PathVariable("id") Integer facultyId,
                                                         @RequestBody TeacherRequestDto requestDto) {
        return ResponseEntity.ok(facultyService.addTeacherToFaculty(facultyId,requestDto));
    }

    @PostMapping("/{id}/student")
    public ResponseEntity<StudentResponseDto> addStudent(@PathVariable("id") Integer facultyId,
                                                         @RequestBody StudentRequestDto requestDto) {
        return ResponseEntity.ok(facultyService.addStudentToFaculty(
                facultyId,
                requestDto));
    }
}
