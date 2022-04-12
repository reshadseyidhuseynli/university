package com.company.service.impl;

import com.company.entity.Lesson;
import com.company.repository.LessonRepository;
import com.company.service.inter.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository repo;

    @Override
    public Lesson getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<Lesson> getAll() {
        return repo.findAll();
    }

    @Override
    public Lesson addOrUpdate(Lesson entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Lesson entity) {
        repo.delete(entity);
    }
}
