package com.company.util.enamerations;

public enum AcademicRank {
    INSTRUCTOR(1), ASSISTANT_PROFESSOR(2), ASSOCIATE_PROFESSOR(3), PROFESSOR(4);

    final int id;

    AcademicRank(int id){
        this.id = id;
    }
}
