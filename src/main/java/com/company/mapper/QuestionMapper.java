package com.company.mapper;

import com.company.dto.QuestionDto;
import com.company.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toQuestionDto(Question question);

    List<QuestionDto> toQuestionDtoList(List<Question> questionList);

}
