package com.company.mapper;

import com.company.dto.LessonDto;
import com.company.entity.Lesson;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonDto toLessonDto(Lesson lesson);

    List<LessonDto> toLessonDtoList(List<Lesson> lessonList);

}
