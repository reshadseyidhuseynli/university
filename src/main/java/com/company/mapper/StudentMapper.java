package com.company.mapper;

import com.company.dto.response.StudentResponseDto;
import com.company.entity.Student;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface StudentMapper {

    StudentResponseDto toStudentDto(Student student);

    List<StudentResponseDto> toStudentDtoList(List<Student> studentList);

}
