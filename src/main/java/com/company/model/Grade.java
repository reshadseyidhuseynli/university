package com.company.model;

import com.company.error.EnumException;

public enum Grade {

    BACHELOR_I(1),
    BACHELOR_II(2),
    BACHELOR_III(3),
    BACHELOR_IV(4),
    GRADUATE_BACHELOR(5),
    MASTER_I(6),
    MASTER_II(7),
    GRADUATE_MASTER(8);

    private final Integer value;
    private final Grade[] grades = values();

    Grade(Integer value){

        this.value = value;
    }

    public static Grade getInstanceByValue(Integer value) {

        if (value == 1)
            return Grade.BACHELOR_I;
        else if (value == 2)
            return Grade.BACHELOR_II;
        else if (value == 3)
            return Grade.BACHELOR_III;
        else if (value == 4)
            return Grade.BACHELOR_IV;
        else if (value == 5)
            return Grade.GRADUATE_BACHELOR;
        else if (value == 6)
            return Grade.MASTER_I;
        else if (value == 7)
            return Grade.MASTER_II;
        else if (value == 8)
            return Grade.GRADUATE_MASTER;
        else throw new EnumException("Not found grade by this value: " + value);

    }

    public Integer getValue() {
        return value;
    }

    public Grade passNextYear(Integer year) {

        if (this != Grade.GRADUATE_BACHELOR && this != Grade.GRADUATE_MASTER) {
            return grades[ordinal() + year];
        } else {
            return this;
        }

    }

}
