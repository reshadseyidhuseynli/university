package com.company.restful;

import com.company.database.entity.*;
import com.company.database.service.inter.FacultyService;
import com.company.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FacultyController {

    @Autowired
    private FacultyService service;

    @GetMapping
    @RequestMapping("/faculties")
    public ResponseEntity<ResponseDTO> getAll(){
        List<FacultyDTO> response = new ArrayList<>();
        for (Faculty f : service.getAll()){
            response.add(new FacultyDTO(f));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id){
        FacultyDTO response = new FacultyDTO(service.getById(id));
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}/head")
    public ResponseEntity<ResponseDTO> getTheFacultyHead(@PathVariable("id") Integer id){
        TeacherDTO response = new TeacherDTO(service.getById(id).getHead());
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}/lessons")
    public ResponseEntity<ResponseDTO> getTheFacultyLessons(@PathVariable("id") Integer id){
        List<LessonDTO> response = new ArrayList<>();
        for (Lesson l : service.getById(id).getLessons()){
            response.add(new LessonDTO(l));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}/teachers")
    public ResponseEntity<ResponseDTO> getTheFacultyTeachers(@PathVariable("id") Integer id){
        List<TeacherDTO> response = new ArrayList<>();
        for (Teacher t : service.getById(id).getTeachers()){
            response.add(new TeacherDTO(t));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}/students")
    public ResponseEntity<ResponseDTO> getTheFacultyStudents(@PathVariable("id") Integer id){
        List<StudentDTO> response = new ArrayList<>();
        for (Student s : service.getById(id).getStudents()){
            response.add(new StudentDTO(s));
        }
        return ResponseEntity.ok(ResponseDTO.createResponse(response));
    }

    @DeleteMapping
    @RequestMapping("/faculties/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id){
        Faculty faculty = service.getById(id);
        service.delete(faculty);
        FacultyDTO response = new FacultyDTO(faculty);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully deleted"));
    }

    @GetMapping
    @RequestMapping("/faculties")
    public ResponseEntity<ResponseDTO> add(@RequestParam(value = "name") String name){
        Faculty faculty = new Faculty(name);
        service.addOrUpdate(faculty);
        FacultyDTO response = new FacultyDTO(faculty);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully added"));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}/add+lesson")
    public ResponseEntity<ResponseDTO> addLesson(@PathVariable("id") Integer id,
                                                 @RequestParam(value = "name") String name){
        Faculty faculty = service.getById(id);
        Lesson newLesson = new Lesson(name, faculty);
        faculty.getLessons().add(newLesson);
        service.addOrUpdate(faculty);
        LessonDTO response = new LessonDTO(newLesson);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully added"));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}/add+teacher")
    public ResponseEntity<ResponseDTO> addTeacher(@PathVariable("id") Integer id,
                                                  @RequestParam(value = "name") String name,
                                                  @RequestParam(value = "surname") String surname,
                                                  @RequestParam(value = "birthdate") LocalDate birthdate){
        Faculty faculty = service.getById(id);
        Teacher newTeacher = new Teacher(name, surname,birthdate, faculty);
        faculty.getTeachers().add(newTeacher);
        service.addOrUpdate(faculty);
        TeacherDTO response = new TeacherDTO(newTeacher);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully added"));
    }

    @GetMapping
    @RequestMapping("/faculties/{id}/add+student")
    public ResponseEntity<ResponseDTO> addStudent(@PathVariable("id") Integer id,
                                                  @RequestParam(value = "name") String name,
                                                  @RequestParam(value = "surname") String surname,
                                                  @RequestParam(value = "birthdate") LocalDate birthdate){
        Faculty faculty = service.getById(id);
        Student newStudent = new Student(name, surname, birthdate, faculty);
        faculty.getStudents().add(newStudent);
        service.addOrUpdate(faculty);
        StudentDTO response = new StudentDTO(newStudent);
        return ResponseEntity.ok(ResponseDTO.createResponse(response, "successfully added"));
    }
}
