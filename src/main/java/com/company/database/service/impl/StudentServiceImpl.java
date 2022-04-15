package com.company.database.service.impl;

import com.company.database.entity.Student;
import com.company.database.repository.StudentRepository;
import com.company.database.service.inter.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repo;

    @Override
    public Student getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public Student addOrUpdate(Student entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Student entity) {
        repo.delete(entity);
    }
}
