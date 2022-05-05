package com.company.controller;

import com.company.service.GradeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class GradeController {

    private final GradeService gradeService;

    GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PutMapping("/grade")
    public void passNextYearAll() {

        gradeService.passNextYear();
    }

    @PutMapping("/{id}/grade")
    public void passNextYearAll(@PathVariable Integer id,
                                @RequestParam(required = false) Integer year) {

        gradeService.passNextYearByStudentId(id, year);
    }
}
