package com.company.domain;

import com.company.error.MissingItemException;

public enum AcademicRank {

    INSTRUCTOR(1),
    ASSISTANT_PROFESSOR(2),
    ASSOCIATE_PROFESSOR(3),
    PROFESSOR(4);

    private final Integer value;

    AcademicRank(Integer value) {
        this.value = value;
    }

    public static AcademicRank getInstanceByValue(Integer value) {
        if (value == 1)
            return AcademicRank.INSTRUCTOR;
        else if (value == 2)
            return AcademicRank.ASSISTANT_PROFESSOR;
        else if (value == 3)
            return AcademicRank.ASSISTANT_PROFESSOR;
        else if (value == 4)
            return AcademicRank.PROFESSOR;
        else throw new MissingItemException("Not found academic rang by this value: " + value);
    }

    public Integer getValue() {
        return value;
    }

}
