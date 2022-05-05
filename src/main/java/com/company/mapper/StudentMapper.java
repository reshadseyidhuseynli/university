package com.company.mapper;

import com.company.dto.StudentDto;
import com.company.entity.Student;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface StudentMapper {

    StudentDto toStudentDto(Student student);

    List<StudentDto> toStudentDtoList(List<Student> studentList);

}
