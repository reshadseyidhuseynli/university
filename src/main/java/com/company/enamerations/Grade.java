package com.company.enamerations;

public enum Grade {
    BACHELOR_I(),BACHELOR_II(),BACHELOR_III(),BACHELOR_IV(), GRADUATE_BACHELOR(),
    MASTER_I(), MASTER_II(), GRADUATE_MASTER();

    private static final Grade[] grades = values();

    public Grade passNextYear(Integer year){
        if (this != Grade.GRADUATE_BACHELOR && this != Grade.GRADUATE_MASTER){
            return grades[ordinal() + year];
        }else{
            return this;
        }
    }

    @Override
    public  String toString() {
        return "grade:" + this.getClass().getName();
    }
}
