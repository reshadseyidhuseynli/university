package com.company.service;

import com.company.dto.request.FacultyRequestDto;
import com.company.dto.request.StudentRequestDto;
import com.company.dto.request.SubjectRequestDto;
import com.company.dto.request.TeacherRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.dto.response.StudentResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.entity.Faculty;
import com.company.entity.Student;
import com.company.entity.Subject;
import com.company.entity.Teacher;
import com.company.repository.FacultyRepository;
import com.company.repository.StudentRepository;
import com.company.repository.SubjectRepository;
import com.company.repository.TeacherRepository;
import com.company.dto.response.TeacherResponseDto;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.FacultyMapper;
import com.company.mapper.SubjectMapper;
import com.company.mapper.StudentMapper;
import com.company.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final FacultyMapper facultyMapper;
    private final SubjectMapper subjectMapper;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    public List<FacultyResponseDto> getAll() {
        return facultyMapper.toFacultyDtoList(facultyRepository.findAll());
    }

    public FacultyResponseDto getById(Integer id) {
        return facultyMapper.toFacultyDto(findById(id));
    }

    public void delete(Integer id) {
        facultyRepository.deleteById(id);
    }

    public FacultyResponseDto add(FacultyRequestDto requestDto) {
        return facultyMapper.toFacultyDto(
                facultyRepository.save(facultyMapper.toFaculty(requestDto)));
    }

    public SubjectResponseDto addSubjectToFaculty(Integer facultyId, SubjectRequestDto requestDto) {
        Faculty faculty = findById(facultyId);
        Subject subject = subjectMapper.toSubject(requestDto);
        subject.setFaculty(faculty);
        subject = subjectRepository.save(subject);

        return subjectMapper.toSubjectDto(subject);
    }

    public TeacherResponseDto addTeacherToFaculty(Integer facultyId, TeacherRequestDto requestDto) {
        Faculty faculty = findById(facultyId);
        Teacher teacher = teacherMapper.toTeacher(requestDto);
        teacher.setFaculty(faculty);
        teacher = teacherRepository.save(teacher);

        return teacherMapper.toTeacherDto(teacher);
    }

    public StudentResponseDto addStudentToFaculty(Integer facultyId, StudentRequestDto requestDto) {
        Faculty faculty = findById(facultyId);
        Student student = studentMapper.toStudent(requestDto);
        student.setFaculty(faculty);
        student = studentRepository.save(student);

        return studentMapper.toStudentDto(student);
    }

    private Faculty findById(Integer id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.FACULTY_NOT_FOUND,
                        "Faculty not found, by id: " + id));
    }

}
