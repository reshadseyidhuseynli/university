package com.company.mapper;

import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionWithoutAnswerMapper {

    QuestionWithoutAnswerResponseDto toQuestionWithoutAnswerDto(Question question);

    List<QuestionWithoutAnswerResponseDto> toQuestionWithoutAnswerDtoList(List<Question> questionList);
}
