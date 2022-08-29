package com.company.mapper;

import com.company.dto.request.TeacherRequestDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.Teacher;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherResponseDto toTeacherDto(Teacher teacher);

    List<TeacherResponseDto> toTeacherDtoList(List<Teacher> teacherList);

    Teacher toTeacher(TeacherRequestDto teacherRequestDto);

    List<Teacher> toTeacherList(List<TeacherRequestDto> teacherList);

}
