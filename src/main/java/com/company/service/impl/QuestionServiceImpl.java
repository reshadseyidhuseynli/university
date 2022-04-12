package com.company.service.impl;

import com.company.entity.Question;
import com.company.repository.QuestionRepository;
import com.company.service.inter.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository repo;

    @Override
    public Question getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<Question> getAll() {
        return repo.findAll();
    }

    @Override
    public Question addOrUpdate(Question entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Question entity) {
        repo.delete(entity);
    }
}
