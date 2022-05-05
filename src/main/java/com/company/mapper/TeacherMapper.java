package com.company.mapper;

import com.company.dto.TeacherDto;
import com.company.entity.Teacher;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface TeacherMapper {

    TeacherDto toTeacherDto(Teacher teacher);

    List<TeacherDto> toTeacherDtoList(List<Teacher> teacherList);

}
