package com.company.mapper;

import com.company.dto.request.ExamResultRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.entity.ExamResult;
import com.company.entity.Student;
import com.company.entity.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamResultMapperTest {

    private static final Integer ID = 1;
    private static final Integer COUNT_OF_TRUE_ANSWERS = 10;

    private static ExamResult examResult;
    private static ExamResultResponseDto examResultResponseDto;
    private static ExamResultRequestDto examResultRequestDto;
    private static ExamResult newExamResult;

    private final ExamResultMapper examResultMapper = ExamResultMapper.INSTANCE;


    @BeforeAll
    public static void init() {
        examResult = new ExamResult();
        examResult.setId(ID);
        examResult.setStudent(new Student());
        examResult.setSubject(new Subject());
        examResult.setCountOfTrueAnswers(COUNT_OF_TRUE_ANSWERS);

        examResultResponseDto = new ExamResultResponseDto();
        examResultResponseDto.setId(ID);
        examResultResponseDto.setSubject(new SubjectResponseDto());
        examResultResponseDto.setCountOfTrueAnswers(COUNT_OF_TRUE_ANSWERS);

        examResultRequestDto = new ExamResultRequestDto();
        examResultRequestDto.setTrueAnswerCount(COUNT_OF_TRUE_ANSWERS);

        newExamResult = new ExamResult();
        newExamResult.setCountOfTrueAnswers(COUNT_OF_TRUE_ANSWERS);
        newExamResult.setId(ID);
    }

    @Test
    void toExamResultDtoTest() {
        ExamResult given = examResult;
        ExamResultResponseDto expected = examResultResponseDto;
        ExamResultResponseDto actual = examResultMapper.toExamResultDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toExamResultTest() {
        ExamResultRequestDto given = examResultRequestDto;
        ExamResult expected = newExamResult;

        ExamResult actual = examResultMapper.toExamResult(given);
        actual.setId(ID);
        assertEquals(expected, actual);
    }

    @Test
    void toExamResultDtoListTest() {
        List<ExamResult> given = Collections.singletonList(examResult);
        List<ExamResultResponseDto> expected = Collections.singletonList(examResultResponseDto);
        List<ExamResultResponseDto> actual = examResultMapper.toExamResultDtoList(given);
        assertEquals(expected, actual);
    }

    @Test
    void toExamResultListTest() {
        List<ExamResultRequestDto> given = Collections.singletonList(examResultRequestDto);
        List<ExamResult> expected = Collections.singletonList(newExamResult);

        List<ExamResult> actual = examResultMapper.toExamResultList(given);
        actual.get(0).setId(ID);
        assertEquals(expected, actual);
    }
}
