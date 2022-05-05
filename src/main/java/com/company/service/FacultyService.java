package com.company.service;

import com.company.dto.FacultyDto;
import com.company.dto.LessonDto;
import com.company.dto.StudentDto;
import com.company.dto.TeacherDto;
import com.company.entity.Faculty;
import com.company.entity.Lesson;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.mapper.FacultyMapper;
import com.company.mapper.LessonMapper;
import com.company.mapper.StudentMapper;
import com.company.mapper.TeacherMapper;
import com.company.repository.FacultyRepository;
import com.company.repository.LessonRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final FacultyMapper facultyMapper;
    private final LessonMapper lessonMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    FacultyService(FacultyRepository facultyRepository,
                   LessonRepository lessonRepository,
                   TeacherRepository teacherRepository,
                   StudentRepository studentRepository,
                   FacultyMapper facultyMapper,
                   LessonMapper lessonMapper,
                   TeacherMapper teacherMapper,
                   StudentMapper studentMapper) {

        this.facultyRepository = facultyRepository;
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.facultyMapper = facultyMapper;
        this.lessonMapper = lessonMapper;
        this.teacherMapper = teacherMapper;
        this.studentMapper = studentMapper;
    }

    public List<FacultyDto> getAll() {

        return facultyMapper.toFacultyDtoList(facultyRepository.findAll());
    }

    public FacultyDto getById(Integer id) {

        return facultyMapper.toFacultyDto(facultyRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + id)));
    }

    public FacultyDto delete(Integer id) {

        final Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + id));

        facultyRepository.delete(faculty);

        return facultyMapper.toFacultyDto(faculty);
    }

    public FacultyDto add(String name) {

        return facultyMapper.toFacultyDto(facultyRepository.save(new Faculty(name)));
    }

    public LessonDto addLessonToFaculty(Integer facultyId, String name) {

        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));

        final Lesson lesson = lessonRepository.save(new Lesson(name, faculty));

        return lessonMapper.toLessonDto(lesson);

    }

    public TeacherDto addTeacherToFaculty(Integer facultyId, String name, String surname, LocalDate birthdate) {

        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));

        final Teacher teacher = teacherRepository.save(new Teacher(faculty, name, surname, birthdate));

        return teacherMapper.toTeacherDto(teacher);

    }

    public StudentDto addStudentToFaculty(Integer facultyId, String name, String surname, LocalDate birthdate) {

        final Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new ServiceException("Faculty not found, by id: " + facultyId));

        final Student student = studentRepository.save(new Student(faculty, name, surname, birthdate));

        return studentMapper.toStudentDto(student);

    }

}
