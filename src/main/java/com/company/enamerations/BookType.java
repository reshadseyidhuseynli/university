package com.company.enamerations;

public enum BookType {
    SCIENCE(1), DRAMA(2), HISTORY(3);

    final int id;

    BookType(int id) {
        this.id = id;
    }
}
