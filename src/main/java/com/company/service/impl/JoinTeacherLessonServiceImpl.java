package com.company.service.impl;

import com.company.entity.JoinTeacherLesson;
import com.company.repository.JoinTeacherLessonRepository;
import com.company.service.inter.JoinTeacherLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinTeacherLessonServiceImpl implements JoinTeacherLessonService {

    @Autowired
    private JoinTeacherLessonRepository repo;

    @Override
    public JoinTeacherLesson getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<JoinTeacherLesson> getAll() {
        return repo.findAll();
    }

    @Override
    public JoinTeacherLesson addOrUpdate(JoinTeacherLesson entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(JoinTeacherLesson entity) {
        repo.delete(entity);
    }
}
