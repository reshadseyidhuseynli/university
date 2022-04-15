package com.company.database.service.impl;

import com.company.database.entity.Teacher;
import com.company.database.service.inter.TeacherService;
import com.company.database.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository repo;

    @Override
    public Teacher getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<Teacher> getAll() {
        return repo.findAll();
    }

    @Override
    public Teacher addOrUpdate(Teacher entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Teacher entity) {
        repo.delete(entity);
    }
}
