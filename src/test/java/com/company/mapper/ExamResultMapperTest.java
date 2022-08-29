package com.company.mapper;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.LessonResponseDto;
import com.company.dto.response.TeacherResponseDto;
import com.company.entity.ExamResult;
import com.company.entity.Lesson;
import com.company.entity.Student;
import com.company.entity.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ExamResultMapperTest {

    private final ExamResultMapper examResultMapper = ExamResultMapper.INSTANCE;

    private static ExamResult examResult;
    private static ExamResultResponseDto examResultResponseDto;

    @BeforeAll
    private static void init() {
        examResult = new ExamResult();
        examResult.setId(1);
        examResult.setStudent(new Student());
        examResult.setTeacher(new Teacher());
        examResult.setLesson(new Lesson());
        examResult.setTrueAnswerCount(10);
        examResult.setFalseAnswerCount(0);

        examResultResponseDto = new ExamResultResponseDto();
        examResultResponseDto.setId(1);
        examResultResponseDto.setTeacher(new TeacherResponseDto());
        examResultResponseDto.setLesson(new LessonResponseDto());
        examResultResponseDto.setTrueAnswerCount(10);
        examResultResponseDto.setFalseAnswerCount(0);
    }

    @Test
    void toExamResultDtoTest() {
        ExamResultResponseDto actual = examResultMapper.toExamResultDto(examResult);

        Assertions.assertEquals(examResultResponseDto, actual);
    }

    @Test
    void toExamResultDtoListTest() {
        List<ExamResult> given = new ArrayList<>();
        given.add(examResult);

        List<ExamResultResponseDto> expected = new ArrayList<>();
        expected.add(examResultResponseDto);

        List<ExamResultResponseDto> actual = examResultMapper.toExamResultDtoList(given);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toExamResultTest() {
        ExamResultRequestDto given = new ExamResultRequestDto();
        given.setTrueAnswerCount(10);
        given.setFalseAnswerCount(0);

        ExamResult expected = new ExamResult();
        expected.setId(1000);
        expected.setTrueAnswerCount(10);
        expected.setFalseAnswerCount(0);

        ExamResult actual = examResultMapper.toExamResult(given);
        actual.setId(1000);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toExamResultListTest() {
        ExamResultRequestDto examResultRequestDto = new ExamResultRequestDto();
        examResultRequestDto.setTrueAnswerCount(10);
        examResultRequestDto.setFalseAnswerCount(0);

        ExamResult examResult = new ExamResult();
        examResult.setId(1000);
        examResult.setTrueAnswerCount(10);
        examResult.setFalseAnswerCount(0);

        List<ExamResultRequestDto> given = new ArrayList<>();
        given.add(examResultRequestDto);

        List<ExamResult> expected = new ArrayList<>();
        expected.add(examResult);

        List<ExamResult> actual = examResultMapper.toExamResultList(given);
        actual.get(0).setId(1000);

        Assertions.assertEquals(expected, actual);
    }
}
