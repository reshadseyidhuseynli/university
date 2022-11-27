package com.company.controller;

import com.company.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class GradeController {

    private final GradeService gradeService;

    @PutMapping("/grade")
    public void passNextYearAll() {
        gradeService.passNextYear();
    }

    @PutMapping("/{id}/grade")
    public void passNextYearById(@PathVariable Integer id,
                                @RequestParam(required = false) Integer year) {
        gradeService.passNextYearByStudentId(id, year);
    }
}
