package com.company.service.impl;

import com.company.entity.Book;
import com.company.repository.BookRepository;
import com.company.service.inter.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repo;

    @Override
    public Book getById(Integer id) {
        return repo.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return repo.findAll();
    }

    @Override
    public Book addOrUpdate(Book entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(Book entity) {
        repo.delete(entity);
    }
}
