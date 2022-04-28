package com.company.service;

import com.company.dto.ExamResultDTO;
import com.company.dto.StudentDTO;
import com.company.entity.ExamResult;
import com.company.entity.Faculty;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.repository.ExamResultRepository;
import com.company.repository.FacultyRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ExamResultRepository examResultRepository;
    private final FacultyRepository facultyRepository;

    StudentService(StudentRepository studentRepository,
                   TeacherRepository teacherRepository,
                   ExamResultRepository examResultRepository,
                   FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.examResultRepository = examResultRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<StudentDTO> getAll() {
        final List<Student> all = studentRepository.findAll();

        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : all) {
            studentDTOS.add(new StudentDTO(student));
        }

        return studentDTOS;
    }

    public StudentDTO getById(Integer id) {
        final Student byId = studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Student not found, by id: " + id));

        return new StudentDTO(byId);
    }

    public StudentDTO delete(Integer id) {
        final Student byId = studentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Student not found, by id: " + id));
        studentRepository.delete(byId);

        return new StudentDTO(byId);
    }


    public StudentDTO add(Integer facultyId, String name, String surname, LocalDate birthdate) {
        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));
        Student student = new Student(faculty, name, surname, birthdate);
        studentRepository.save(student);

        return new StudentDTO(student);
    }

    public ExamResultDTO addExamResultToStudent(Integer studentId, Integer teacherId,
                                                Integer trueAnswerCount, Integer falseAnswerCount) {
        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ServiceException("Student not found, by id: " + studentId));
        final Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ServiceException("Teacher not found, by id: " + teacherId));

        ExamResult examResult = new ExamResult(student, teacher, trueAnswerCount, falseAnswerCount);
        examResultRepository.save(examResult);

        return new ExamResultDTO(examResult);
    }
}
