package com.company.domain;

import java.util.stream.Stream;

public enum Grade {

    BACHELOR_I("BACHELOR_I"),
    BACHELOR_II("BACHELOR_II"),
    BACHELOR_III("BACHELOR_III"),
    BACHELOR_IV("BACHELOR_IV"),
    GRADUATE_BACHELOR("GRADUATE_BACHELOR"),
    MASTER_I("MASTER_I"),
    MASTER_II("MASTER_II"),
    GRADUATE_MASTER("GRADUATE_MASTER");

    private final String value;

    Grade(String value) {
        this.value = value;
    }

    public static Grade getInstanceByValue(String value) {
        return Stream.of(Grade.values())
                .filter(grade -> grade.name().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getValue() {
        return value;
    }

    public Grade passNextYear(Integer year) {

        if (this != Grade.GRADUATE_BACHELOR && this != Grade.GRADUATE_MASTER) {
            return values()[ordinal() + year];
        } else {
            return this;
        }

    }

}
