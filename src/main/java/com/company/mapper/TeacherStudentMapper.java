package com.company.mapper;

import com.company.dto.response.StudentListDto;
import com.company.entity.TeacherStudent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherStudentMapper {

    TeacherStudentMapper INSTANCE = Mappers.getMapper(TeacherStudentMapper.class);

    StudentListDto toTeacherStudentDto(TeacherStudent teacherStudent);

    List<StudentListDto> toTeacherStudentDtoList(List<TeacherStudent> teacherStudentList);
}
