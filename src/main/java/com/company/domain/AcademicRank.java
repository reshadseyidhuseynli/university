package com.company.domain;

import com.company.error.MissingItemException;

public enum AcademicRank {

    INSTRUCTOR("INSTRUCTOR"),
    ASSISTANT_PROFESSOR("ASSISTANT_PROFESSOR"),
    ASSOCIATE_PROFESSOR("ASSOCIATE_PROFESSOR"),
    PROFESSOR("PROFESSOR");

    private final String value;

    AcademicRank(String value) {
        this.value = value;
    }

    public static AcademicRank getInstanceByValue(String value) {

        switch (value) {
            case "INSTRUCTOR":
                return AcademicRank.INSTRUCTOR;
            case "ASSISTANT_PROFESSOR":
                return AcademicRank.ASSISTANT_PROFESSOR;
            case "ASSOCIATE_PROFESSOR":
                return AcademicRank.ASSOCIATE_PROFESSOR;
            case "PROFESSOR":
                return AcademicRank.PROFESSOR;
            default:
                throw new MissingItemException("Not found academic rang by this value: " + value);
        }

    }

    public String getValue() {
        return value;
    }

}
