package com.company.mapper;

import com.company.dto.request.LessonRequestDto;
import com.company.dto.response.LessonResponseDto;
import com.company.entity.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    LessonResponseDto toLessonDto(Lesson lesson);

    List<LessonResponseDto> toLessonDtoList(List<Lesson> lessonList);

    Lesson toLesson (LessonRequestDto lessonRequestDto);

    List<Lesson> toLessonList(List<LessonRequestDto> lessonRequestDtoList);

}
