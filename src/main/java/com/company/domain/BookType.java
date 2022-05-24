package com.company.domain;

import com.company.error.MissingItemException;

public enum BookType {

    SCIENCE("SCIENCE"),
    DRAMA("DRAMA"),
    HISTORY("HISTORY");

    private final String value;

    BookType(String value) {
        this.value = value;
    }

    public static BookType getInstanceByValue(String value) {

        switch (value) {
            case "SCIENCE":
                return BookType.SCIENCE;
            case "DRAMA":
                return BookType.DRAMA;
            case "HISTORY":
                return BookType.HISTORY;
            default:
                throw new MissingItemException("Not found book type by this value: " + value);
        }

    }

    public String getValue() {
        return value;
    }

}
