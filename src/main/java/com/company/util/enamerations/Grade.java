package com.company.util.enamerations;

public enum Grade {
    BACHELOR_I(),BACHELOR_II(),BACHELOR_III(),BACHELOR_IV(), GRADUATE_BACHELOR(),
    MASTER_I(), MASTER_II(), GRADUATE_MASTER();

    public static final Grade[] grades = values();

    public Grade passNextYear(){
        if (this != Grade.GRADUATE_BACHELOR && this != Grade.GRADUATE_MASTER){
            return grades[ordinal() + 1];
        }else{
            return this;
        }
    }

    @Override
    public  String toString() {
        return "grade:" + this.getClass().getName();
    }
}
