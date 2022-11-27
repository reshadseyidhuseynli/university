package com.company.mapper;

import com.company.dto.request.FacultyRequestDto;
import com.company.dto.response.FacultyResponseDto;
import com.company.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    FacultyResponseDto toFacultyDto(Faculty faculty);

    Faculty toFaculty(FacultyRequestDto facultyRequestDto);

    List<FacultyResponseDto> toFacultyDtoList(List<Faculty> facultyList);

    List<Faculty> toFacultyList(List<FacultyRequestDto> facultyList);

}
