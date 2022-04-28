package com.company.service;

import com.company.dto.TeacherDTO;
import com.company.entity.Teacher;
import com.company.error.ServiceException;
import com.company.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TeacherService {

    private final TeacherRepository teacherRepository;

    TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDTO> getAll() {
        final List<Teacher> all = teacherRepository.findAll();

        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher teacher : all) {
            teacherDTOS.add(new TeacherDTO(teacher));
        }

        return teacherDTOS;
    }

    public TeacherDTO getById(Integer id) {
        final Teacher byId = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found, by id: " + id));

        return new TeacherDTO(byId);
    }

    public TeacherDTO delete(Integer id) {
        final Teacher byId = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Teacher not found, by id: " + id));
        teacherRepository.delete(byId);

        return new TeacherDTO(byId);
    }
}
