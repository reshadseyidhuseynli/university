package com.company.mapper;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.entity.ExamResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {

    ExamResultMapper INSTANCE = Mappers.getMapper(ExamResultMapper.class);

    ExamResultResponseDto toExamResultDto(ExamResult examResult);

    List<ExamResultResponseDto> toExamResultDtoList(List<ExamResult> examResultList);

    ExamResult toExamResult(ExamResultRequestDto requestDto);

    List<ExamResult> toExamResultList(List<ExamResultRequestDto> requestDtoList);
}
