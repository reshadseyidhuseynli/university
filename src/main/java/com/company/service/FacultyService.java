package com.company.service;

import com.company.dto.FacultyDTO;
import com.company.dto.LessonDTO;
import com.company.dto.StudentDTO;
import com.company.dto.TeacherDTO;
import com.company.entity.Faculty;
import com.company.entity.Lesson;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.repository.FacultyRepository;
import com.company.repository.LessonRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FacultyService{

    private final FacultyRepository facultyRepository;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    FacultyService(FacultyRepository facultyRepository,
                   LessonRepository lessonRepository,
                   TeacherRepository teacherRepository,
                   StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public List<FacultyDTO> getAll() {
        final List<Faculty> all = facultyRepository.findAll();

        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : all) {
            facultyDTOS.add(new FacultyDTO(faculty));
        }

        return facultyDTOS;
    }

    public FacultyDTO getById(Integer id) {
        final Faculty byId = facultyRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + id));

        return new FacultyDTO(byId);
    }

    public FacultyDTO delete(Integer id) {
        final Faculty byId = facultyRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + id));
        facultyRepository.delete(byId);

        return new FacultyDTO(byId);
    }

    public FacultyDTO add(String name) {
        final Faculty faculty = facultyRepository.save(new Faculty(name));

        return new FacultyDTO(faculty);
    }

    public LessonDTO addLessonToFaculty(Integer facultyId, String name) {
        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));
        final Lesson lesson = lessonRepository.save(new Lesson(name, faculty));

        return new LessonDTO(lesson);
    }

    public TeacherDTO addTeacherToFaculty(Integer facultyId, String name, String surname, LocalDate birthdate) {
        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));
        final Teacher teacher = teacherRepository.save(new Teacher(faculty, name, surname, birthdate));

        return new TeacherDTO(teacher);
    }

    public StudentDTO addStudentToFaculty(Integer facultyId, String name, String surname, LocalDate birthdate) {
        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));
        final Student student = studentRepository.save(new Student(faculty, name, surname, birthdate));

        return new StudentDTO(student);
    }
}
