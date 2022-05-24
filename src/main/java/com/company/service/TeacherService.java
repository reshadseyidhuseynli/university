package com.company.service;

import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Teacher;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.TeacherMapper;
import com.company.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public List<TeacherResponseDto> getAll() {
        return teacherMapper.toTeacherDtoList(teacherRepository.findAll());
    }

    public TeacherResponseDto getById(Integer id) {
        return teacherMapper.toTeacherDto(findByIdIfAvailable(id));
    }

    public TeacherResponseDto delete(Integer id) {
        final Teacher teacher = findByIdIfAvailable(id);
        teacherRepository.delete(teacher);

        return teacherMapper.toTeacherDto(teacher);
    }

    private Teacher findByIdIfAvailable(Integer id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.TEACHER_NOT_FOUND,
                        "Teacher not found, by id: " + id));
    }

}
