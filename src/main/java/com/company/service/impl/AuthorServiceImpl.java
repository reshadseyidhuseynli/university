package com.company.service.impl;

import com.company.entity.Author;
import com.company.repository.AuthorRepository;
import com.company.service.inter.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repo;

    @Override
    public Author getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return repo.findAll();
    }

    @Override
    public Author addOrUpdate(Author entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Author entity) {
        repo.delete(entity);
    }
}
