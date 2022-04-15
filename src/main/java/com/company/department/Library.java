package com.company.department;

import com.company.database.entity.Book;

import java.util.*;

public class Library {
    public static final Library library = new Library();
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }

    public Book[] getBooks(){
        return books.toArray(new Book[books.size()]);
    }
}
