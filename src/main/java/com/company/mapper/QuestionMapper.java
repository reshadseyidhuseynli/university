package com.company.mapper;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    QuestionResponseDto toQuestionDto(Question question);

    List<QuestionResponseDto> toQuestionDtoList(List<Question> questionList);

    Question toQuestion(QuestionRequestDto question);

    List<Question> toQuestionList(List<QuestionRequestDto> questionRequestDtoList);

    QuestionWithoutAnswerResponseDto toQuestionWithoutAnswerDto(Question question);

    List<QuestionWithoutAnswerResponseDto> toQuestionWithoutAnswerDtoList(List<Question> questionList);

}
