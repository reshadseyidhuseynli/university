package com.company.util.enamerations;

public enum BookType {
    SCIENCE(1), DRAMA(2), HISTORY(3);

    private int id;

    BookType(int id) {
        this.id = id;
    }
}