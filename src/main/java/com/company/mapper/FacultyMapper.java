package com.company.mapper;

import com.company.dto.response.FacultyResponseDto;
import com.company.entity.Faculty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    FacultyResponseDto toFacultyDto(Faculty faculty);

    List<FacultyResponseDto> toFacultyDtoList(List<Faculty> facultyList);

}
