package com.company.service.impl;

import com.company.entity.Faculty;
import com.company.repository.FacultyRepository;
import com.company.service.inter.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository repo;

    @Override
    public Faculty getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<Faculty> getAll() {
        return repo.findAll();
    }

    @Override
    public Faculty addOrUpdate(Faculty entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Faculty entity) {
        repo.delete(entity);
    }
}
