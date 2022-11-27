package com.company.controller;

import com.company.dto.request.FacultyRequestDto;
import com.company.dto.request.StudentRequestDto;
import com.company.dto.request.SubjectRequestDto;
import com.company.dto.request.TeacherRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public List<FacultyResponseDto> getAll() {
        return facultyService.getAll();
    }

    @GetMapping("/{id}")
    public FacultyResponseDto getById(@PathVariable Integer id) {
        return facultyService.getById(id);
    }

    @GetMapping("/{id}/head")
    public TeacherResponseDto getFacultyHeadById(@PathVariable Integer id) {
        return facultyService.getById(id).getHead();
    }

    @GetMapping("/{id}/subjects")
    public List<SubjectResponseDto> getFacultySubjectsById(@PathVariable Integer id) {
        return facultyService.getById(id).getSubjects();
    }

    @GetMapping("/{id}/teachers")
    public List<TeacherResponseDto> getFacultyTeachersById(@PathVariable Integer id) {
        return facultyService.getById(id).getTeachers();
    }

    @GetMapping("/{id}/students")
    public List<StudentResponseDto> getFacultyStudentsById(@PathVariable Integer id) {
        return facultyService.getById(id).getStudents();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        facultyService.delete(id);
    }

    @PostMapping
    public FacultyResponseDto add(@RequestBody FacultyRequestDto requestDto) {
        return facultyService.add(requestDto);
    }

    @PostMapping("/{id}/subjects")
    public SubjectResponseDto addSubject(@PathVariable("id") Integer facultyId,
                                        @RequestBody SubjectRequestDto requestDto) {
        return facultyService.addSubjectToFaculty(facultyId, requestDto);
    }

    @PostMapping("/{id}/teachers")
    public TeacherResponseDto addTeacher(@PathVariable("id") Integer facultyId,
                                         @RequestBody TeacherRequestDto requestDto) {
        return facultyService.addTeacherToFaculty(facultyId, requestDto);
    }

    @PostMapping("/{id}/students")
    public StudentResponseDto addStudent(@PathVariable("id") Integer facultyId,
                                         @RequestBody StudentRequestDto requestDto) {
        return facultyService.addStudentToFaculty(facultyId, requestDto);
    }
}
