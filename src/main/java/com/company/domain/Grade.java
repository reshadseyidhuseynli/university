package com.company.domain;

import com.company.error.MissingItemException;

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

        switch (value) {
            case "BACHELOR_I":
                return Grade.BACHELOR_I;
            case "BACHELOR_II":
                return Grade.BACHELOR_II;
            case "BACHELOR_III":
                return Grade.BACHELOR_III;
            case "BACHELOR_IV":
                return Grade.BACHELOR_IV;
            case "GRADUATE_BACHELOR":
                return Grade.GRADUATE_BACHELOR;
            case "MASTER_I":
                return Grade.MASTER_I;
            case "MASTER_II":
                return Grade.MASTER_II;
            case "GRADUATE_MASTER":
                return Grade.GRADUATE_MASTER;
            default:
                throw new MissingItemException("Not found grade by this value: " + value);
        }

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
