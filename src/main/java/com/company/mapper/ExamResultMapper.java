package com.company.mapper;

import com.company.dto.ExamResultDto;
import com.company.entity.ExamResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExamResultMapper {

    ExamResultDto toExamResultDto(ExamResult examResult);

    List<ExamResultDto> toExamResultDtoList(List<ExamResult> examResultList);

}
