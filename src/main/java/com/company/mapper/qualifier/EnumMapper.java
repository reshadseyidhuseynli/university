package com.company.mapper.qualifier;

import com.company.domain.AcademicRank;
import com.company.domain.Grade;
import com.company.domain.BookType;
import org.springframework.stereotype.Component;

@Component
public class EnumMapper {

    public AcademicRank mapToAcademicRank(String value) {
        return AcademicRank.getInstanceByValue(value);
    }

    public BookType mapToBookType(String value) {
        return BookType.getInstanceByValue(value);
    }

    public Grade mapToGrade(String value){
        return Grade.getInstanceByValue(value);
    }

    public String mapToValue(AcademicRank academicRank) {
        return academicRank.getValue();
    }

    public String mapToValue(BookType bookType){
        return bookType.getValue();
    }

    public String mapToValue(Grade grade){
        return grade.getValue();
    }

}
