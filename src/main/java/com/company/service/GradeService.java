package com.company.service;

import com.company.entity.Student;
import com.company.repository.StudentRepository;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class GradeService {

    private static final Integer ONE_YEAR = 1;
    private final StudentRepository studentRepository;

    public void passNextYear() {
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            student.setGrade(student.getGrade().passNextYear(ONE_YEAR));
            studentRepository.save(student);
        }
    }

    public void passNextYearByStudentId(Integer studentId, Integer year) {
        if (Objects.isNull(year)) {
            year = ONE_YEAR;
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ServiceException(
                ErrorCode.STUDENT_NOT_FOUND,
                "Student not found, by id: " + studentId));
        student.setGrade(student.getGrade().passNextYear(year));
        studentRepository.save(student);
    }

}
