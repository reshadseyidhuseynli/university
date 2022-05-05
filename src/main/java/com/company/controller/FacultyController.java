package com.company.controller;

import com.company.dto.FacultyDto;
import com.company.dto.LessonDto;
import com.company.dto.StudentDto;
import com.company.dto.TeacherDto;
import com.company.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAll() {

        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getById(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.getById(id));
    }

    @GetMapping("/{id}/head")
    public ResponseEntity<TeacherDto> getFacultyHead(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.getById(id).getHead());
    }

    @GetMapping("/{id}/lessons")
    public ResponseEntity<List<LessonDto>> getFacultyLessons(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.getById(id).getLessons());
    }

    @GetMapping("/{id}/teachers")
    public ResponseEntity<List<TeacherDto>> getFacultyTeachers(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.getById(id).getTeachers());
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDto>> getFacultyStudents(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.getById(id).getStudents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacultyDto> delete(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(facultyService.delete(id));
    }

    @PostMapping
    public ResponseEntity<FacultyDto> add(@RequestParam(value = "name") String name) {

        return ResponseEntity.ok(facultyService.add(name));
    }

    @PostMapping("/{id}/add+lesson")
    public ResponseEntity<LessonDto> addLesson(@PathVariable("id") Integer facultyId,
                                               @RequestParam(value = "name") String name) {

        return ResponseEntity.ok(facultyService.addLessonToFaculty(facultyId, name));
    }

    @PostMapping("/{id}/add+teacher")
    public ResponseEntity<TeacherDto> addTeacher(@PathVariable("id") Integer facultyId,
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "surname") String surname,
                                                 @RequestParam(value = "birthdate") LocalDate birthdate) {

        return ResponseEntity.ok(facultyService.addTeacherToFaculty(facultyId, name, surname, birthdate));
    }

    @PostMapping("/{id}/add+student")
    public ResponseEntity<StudentDto> addStudent(@PathVariable("id") Integer facultyId,
                                                 @RequestParam(value = "name") String name,
                                                 @RequestParam(value = "surname") String surname,
                                                 @RequestParam(value = "birthdate") LocalDate birthdate) {

        return ResponseEntity.ok(facultyService.addStudentToFaculty(facultyId, name, surname, birthdate));
    }
}
