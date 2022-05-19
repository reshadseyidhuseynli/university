package com.company.mapper.qualifier;

import com.company.domain.AcademicRank;
import com.company.domain.BookType;
import com.company.domain.Grade;

public class EnumMapper {

    public AcademicRank mapToAcademicRank(Integer value) {
        return AcademicRank.getInstanceByValue(value);
    }

    public BookType mapToBookType(Integer value) {
        return BookType.DRAMA.getInstanceByValue(value);
    }

    public Grade mapToGrade(Integer value){
        return Grade.getInstanceByValue(value);
    }

    public Integer mapToValue(AcademicRank academicRank) {
        return academicRank.getValue();
    }

    public Integer mapToValue(BookType bookType){
        return bookType.getValue();
    }

    public Integer mapToValue(Grade grade){
        return grade.getValue();
    }

}
