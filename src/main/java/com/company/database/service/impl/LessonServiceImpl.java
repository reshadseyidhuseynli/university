package com.company.database.service.impl;

import com.company.database.entity.Lesson;
import com.company.database.repository.LessonRepository;
import com.company.database.service.inter.LessonService;
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
