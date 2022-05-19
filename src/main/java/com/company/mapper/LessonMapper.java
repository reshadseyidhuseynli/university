package com.company.mapper;

import com.company.dto.response.LessonResponseDto;
import com.company.entity.Lesson;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponseDto toLessonDto(Lesson lesson);

    List<LessonResponseDto> toLessonDtoList(List<Lesson> lessonList);

}
