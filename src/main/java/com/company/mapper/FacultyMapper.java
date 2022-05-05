package com.company.mapper;

import com.company.dto.FacultyDto;
import com.company.entity.Faculty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    FacultyDto toFacultyDto(Faculty faculty);

    List<FacultyDto> toFacultyDtoList(List<Faculty> facultyList);

}
