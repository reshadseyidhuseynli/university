package com.company.mapper;

import com.company.dto.request.LessonRequestDto;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.entity.Faculty;
import com.company.entity.Lesson;
import com.company.entity.Question;
import com.company.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class LessonMapperTest {

    private final LessonMapper lessonMapper = LessonMapper.INSTANCE;

    private static Lesson lesson;
    private static LessonResponseDto lessonResponseDto;

    @BeforeAll
    private static void init() {
        lesson = new Lesson();
        lesson.setId(1);
        lesson.setName("testLesson");
        lesson.setFaculty(new Faculty());
        lesson.setTeachers(new ArrayList<>(Collections.singletonList(new Teacher())));
        lesson.setQuestions(new ArrayList<>(Collections.singletonList(new Question())));

        lessonResponseDto = new LessonResponseDto();
        lessonResponseDto.setId(1);
        lessonResponseDto.setName("testLesson");
        lessonResponseDto.setQuestions(new ArrayList<>(Collections
                .singletonList(new QuestionWithoutAnswerResponseDto())));
    }

    @Test
    void toLessonDtoTest() {
        LessonResponseDto actual = lessonMapper.toLessonDto(lesson);

        Assertions.assertEquals(lessonResponseDto, actual);
    }

    @Test
    void toLessonDtoListTest() {
        List<Lesson> given = new ArrayList<>();
        given.add(lesson);

        List<LessonResponseDto> expected = new ArrayList<>();
        expected.add(lessonResponseDto);

        List<LessonResponseDto> actual = lessonMapper.toLessonDtoList(given);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toLessonTest() {
        LessonRequestDto given = new LessonRequestDto();
        given.setName("testLesson");
        Lesson expected = new Lesson();
        expected.setId(1000);
        expected.setName("testLesson");

        Lesson actual = lessonMapper.toLesson(given);
        actual.setId(1000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toLessonListTest() {
        LessonRequestDto lessonRequestDto = new LessonRequestDto();
        lessonRequestDto.setName("testLesson");
        Lesson lesson = new Lesson();
        lesson.setId(1000);
        lesson.setName("testLesson");

        List<LessonRequestDto> given = new ArrayList<>();
        given.add(lessonRequestDto);

        List<Lesson> expected = new ArrayList<>();
        expected.add(lesson);

        List<Lesson> actual = lessonMapper.toLessonList(given);
        actual.get(0).setId(1000);

        Assertions.assertEquals(expected, actual);
    }
}
