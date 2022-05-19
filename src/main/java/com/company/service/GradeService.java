package com.company.service;

import com.company.entity.Student;
import com.company.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class GradeService {

    private static final Integer ONE_YEAR = 1;
    private final StudentRepository studentRepository;

    GradeService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void passNextYear() {

        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            student.setGrade(student.getGrade().passNextYear(ONE_YEAR));
        }

    }

    public void passNextYearByStudentId(Integer studentId, Integer year) {

        if (Objects.isNull(year)) {
            year = ONE_YEAR;
        }

        Student student = studentRepository.getById(studentId);
        student.setGrade(student.getGrade().passNextYear(year));

    }

}
