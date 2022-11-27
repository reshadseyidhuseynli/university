package com.company.mapper;

import com.company.dto.request.SubjectRequestDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectResponseDto toSubjectDto(Subject subject);

    List<SubjectResponseDto> toSubjectDtoList(List<Subject> subjectList);

    Subject toSubject (SubjectRequestDto subjectRequestDto);

    List<Subject> toSubjectList(List<SubjectRequestDto> subjectRequestDtoList);

}
