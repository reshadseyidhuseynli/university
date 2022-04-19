package com.company.department;

import com.company.database.entity.Book;
import com.company.database.service.impl.BookServiceImpl;
import com.company.database.service.inter.AuthorService;
import com.company.database.service.inter.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class Library {
    public static final Library of = new Library();

    @Autowired
    private BookService bookService;

    private final List<Book> books = bookService.getAll();

    private Library(){}

    public void addBook(Book book){
        bookService.addOrUpdate(book);
        books.add(book);
    }

    public Book[] getBooks(){
        return books.toArray(new Book[books.size()]);
    }
}
