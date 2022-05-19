package com.company.mapper;

import com.company.dto.response.QuestionWithAnswerResponseDto;
import com.company.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionWithAnswerMapper {

    QuestionWithAnswerResponseDto toQuestionWithAnswerDto(Question question);

    List<QuestionWithAnswerResponseDto> toQuestionWithAnswerDtoList(List<Question> questionList);



}
