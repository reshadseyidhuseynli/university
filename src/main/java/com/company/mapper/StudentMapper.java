package com.company.mapper;

import com.company.dto.request.StudentRequestDto;
import com.company.dto.response.StudentResponseDto;
import com.company.entity.Student;
import com.company.mapper.qualifier.EnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = EnumMapper.class)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentResponseDto toStudentDto(Student student);

    List<StudentResponseDto> toStudentDtoList(List<Student> studentList);

    Student toStudent(StudentRequestDto student);

    List<Student> toStudentList(List<StudentRequestDto> studentList);

}
