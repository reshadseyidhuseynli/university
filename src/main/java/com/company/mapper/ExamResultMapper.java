package com.company.mapper;

import com.company.dto.response.ExamResultResponseDto;
import com.company.entity.ExamResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {

    ExamResultResponseDto toExamResultDto(ExamResult examResult);

    List<ExamResultResponseDto> toExamResultDtoList(List<ExamResult> examResultList);

}
