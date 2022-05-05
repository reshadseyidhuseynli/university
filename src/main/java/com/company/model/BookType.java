package com.company.model;

import com.company.error.EnumException;

public enum BookType {

    SCIENCE(1),
    DRAMA(2),
    HISTORY(3);

    private final Integer value;

    BookType(Integer value) {

        this.value = value;
    }

    public BookType getInstanceByValue(Integer value){

        if (value == 1)
            return BookType.SCIENCE;
        else if (value == 2)
            return BookType.DRAMA;
        else if(value == 3)
            return BookType.HISTORY;
        else throw new EnumException("Not found book type by this value: " + value);

    }

    public Integer getValue() {
        return value;
    }

}
