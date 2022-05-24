package com.company.service;

import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.dto.request.FacultyRequestDto;
import com.company.dto.request.LessonRequestDto;
import com.company.dto.request.StudentRequestDto;
import com.company.dto.request.TeacherRequestDto;
import com.company.entity.Faculty;
import com.company.entity.Lesson;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.FacultyMapper;
import com.company.mapper.LessonMapper;
import com.company.mapper.StudentMapper;
import com.company.mapper.TeacherMapper;
import com.company.repository.FacultyRepository;
import com.company.repository.LessonRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final FacultyMapper facultyMapper;
    private final LessonMapper lessonMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    public List<FacultyResponseDto> getAll() {
        return facultyMapper.toFacultyDtoList(facultyRepository.findAll());
    }

    public FacultyResponseDto getById(Integer id) {
        return facultyMapper.toFacultyDto(findByIdIfAvailable(id));
    }

    public FacultyResponseDto delete(Integer id) {
        final Faculty faculty = findByIdIfAvailable(id);
        facultyRepository.delete(faculty);

        return facultyMapper.toFacultyDto(faculty);
    }

    public FacultyResponseDto add(FacultyRequestDto requestDto) {
        return facultyMapper.toFacultyDto(
                facultyRepository.save(new Faculty(requestDto.getName())));
    }

    public LessonResponseDto addLessonToFaculty(Integer facultyId, LessonRequestDto requestDto) {
        final Faculty faculty = findByIdIfAvailable(facultyId);
        final Lesson lesson = lessonRepository.save(
                new Lesson(requestDto.getName(), faculty));

        return lessonMapper.toLessonDto(lesson);
    }

    public TeacherResponseDto addTeacherToFaculty(Integer facultyId, TeacherRequestDto requestDto) {
        final Faculty faculty = findByIdIfAvailable(facultyId);
        final Teacher teacher = teacherRepository.save(
                new Teacher(
                        faculty,
                        requestDto.getName(),
                        requestDto.getSurname(),
                        requestDto.getBirthdate()));

        return teacherMapper.toTeacherDto(teacher);
    }

    public StudentResponseDto addStudentToFaculty(Integer facultyId, StudentRequestDto requestDto) {
        final Faculty faculty = findByIdIfAvailable(facultyId);
        final Student student = studentRepository.save(
                new Student(
                        faculty,
                        requestDto.getName(),
                        requestDto.getSurname(),
                        requestDto.getBirthdate()));

        return studentMapper.toStudentDto(student);
    }

    private Faculty findByIdIfAvailable(Integer id){
        return facultyRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.FACULTY_NOT_FOUND,
                        "Faculty not found, by id: " + id));
    }

}
