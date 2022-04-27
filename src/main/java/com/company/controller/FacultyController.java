package com.company.controller;

import com.company.dto.*;
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
    public ResponseEntity<ResponseDTO> getAll() {
        final List<FacultyDTO> all = facultyService.getAll();

        return ResponseEntity.ok(ResponseDTO.create(all));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        final FacultyDTO byId = facultyService.getById(id);

        return ResponseEntity.ok(ResponseDTO.create(byId));
    }

    @GetMapping("/{id}/head")
    public ResponseEntity<ResponseDTO> getFacultyHead(@PathVariable("id") Integer id) {
        final TeacherDTO head = facultyService.getById(id).getHead();

        return ResponseEntity.ok(ResponseDTO.create(head));
    }

    @GetMapping("/{id}/lessons")
    public ResponseEntity<ResponseDTO> getFacultyLessons(@PathVariable("id") Integer id) {
        final List<LessonDTO> lessons = facultyService.getById(id).getLessons();

        return ResponseEntity.ok(ResponseDTO.create(lessons));
    }

    @GetMapping("/{id}/teachers")
    public ResponseEntity<ResponseDTO> getFacultyTeachers(@PathVariable("id") Integer id) {
        final List<TeacherDTO> teachers = facultyService.getById(id).getTeachers();

        return ResponseEntity.ok(ResponseDTO.create(teachers));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<ResponseDTO> getFacultyStudents(@PathVariable("id") Integer id) {
        final List<StudentDTO> students = facultyService.getById(id).getStudents();

        return ResponseEntity.ok(ResponseDTO.create(students));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        final FacultyDTO facultyDTO = facultyService.delete(id);

        return ResponseEntity.ok(ResponseDTO.create(facultyDTO, "The faculty successfully deleted"));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> add(@RequestParam(value = "name") String name) {
        final FacultyDTO facultyDTO = facultyService.add(name);

        return ResponseEntity.ok(ResponseDTO.create(facultyDTO,"The faculty successfully added"));
    }

    @PostMapping("/{id}/add+lesson")
    public ResponseEntity<ResponseDTO> addLesson(@PathVariable("id") Integer facultyId,
                                                 @RequestParam(value = "name") String name) {
        final LessonDTO lessonDTO = facultyService.addLessonToFaculty(facultyId, name);

        return ResponseEntity.ok(ResponseDTO.create(lessonDTO, "The lesson successfully added"));
    }

    @PostMapping("/{id}/add+teacher")
    public ResponseEntity<ResponseDTO> addTeacher(@PathVariable("id") Integer facultyId,
                                                  @RequestParam(value = "name") String name,
                                                  @RequestParam(value = "surname") String surname,
                                                  @RequestParam(value = "birthdate") LocalDate birthdate) {
        final TeacherDTO teacherDTO = facultyService.addTeacherToFaculty(facultyId, name, surname, birthdate);

        return ResponseEntity.ok(ResponseDTO.create(teacherDTO, "The teacher successfully added"));
    }

    @PostMapping("/{id}/add+student")
    public ResponseEntity<ResponseDTO> addStudent(@PathVariable("id") Integer facultyId,
                                                  @RequestParam(value = "name") String name,
                                                  @RequestParam(value = "surname") String surname,
                                                  @RequestParam(value = "birthdate") LocalDate birthdate) {
        final StudentDTO studentDTO = facultyService.addStudentToFaculty(facultyId, name, surname, birthdate);

        return ResponseEntity.ok(ResponseDTO.create(studentDTO, "The student successfully added"));
    }
}
