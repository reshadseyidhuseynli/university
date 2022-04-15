package com.company.database.service.impl;

import com.company.database.entity.JoinTeacherStudent;
import com.company.database.repository.JoinTeacherStudentRepository;
import com.company.database.service.inter.JoinTeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinTeacherStudentServiceImpl implements JoinTeacherStudentService {

    @Autowired
    private JoinTeacherStudentRepository repo;

    @Override
    public JoinTeacherStudent getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<JoinTeacherStudent> getAll() {
        return repo.findAll();
    }

    @Override
    public JoinTeacherStudent addOrUpdate(JoinTeacherStudent entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(JoinTeacherStudent entity) {
        repo.save(entity);
    }
}
