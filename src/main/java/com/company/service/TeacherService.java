package com.company.service;

import com.company.dto.TeacherDto;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.mapper.TeacherMapper;
import com.company.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    TeacherService(TeacherRepository teacherRepository,
                   TeacherMapper teacherMapper) {

        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public List<TeacherDto> getAll() {

        return teacherMapper.toTeacherDtoList(teacherRepository.findAll());
    }

    public TeacherDto getById(Integer id) {
        
        return teacherMapper.toTeacherDto(teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found, by id: " + id)));
    }

    public TeacherDto delete(Integer id) {

        final Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found, by id: " + id));

        teacherRepository.delete(teacher);

        return teacherMapper.toTeacherDto(teacher);

    }

}
