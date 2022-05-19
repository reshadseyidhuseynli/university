package com.company.mapper;

import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Teacher;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface TeacherMapper {

    TeacherResponseDto toTeacherDto(Teacher teacher);

    List<TeacherResponseDto> toTeacherDtoList(List<Teacher> teacherList);

}
