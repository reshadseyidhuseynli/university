package com.company.service;

import com.company.dto.response.ExamResultResponseDto;
import com.company.entity.ExamResult;
import com.company.error.ServiceException;
import com.company.mapper.ExamResultMapper;
import com.company.repository.ExamResultRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ExamResultServiceTest {

    @Mock
    private ExamResultRepository examResultRepository;

    @Mock
    private ExamResultMapper examResultMapper;

    @InjectMocks
    private ExamResultService examResultService;

    private static ExamResult examResult;
    private static ExamResultResponseDto examResultResponseDto;

    @BeforeAll
    private static void init() {
        examResult = new ExamResult();
        examResultResponseDto = new ExamResultResponseDto();
    }

    @Test
    void getAllTest() {
        List<ExamResult> examResultList = new ArrayList<>();
        List<ExamResultResponseDto> expected = new ArrayList<>();

        Mockito.when(examResultRepository.findAll())
                .thenReturn(examResultList);
        Mockito.when(examResultMapper.toExamResultDtoList(examResultList))
                .thenReturn(expected);

        List<ExamResultResponseDto> actual = examResultService.getAll();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(examResultRepository, Mockito.times(1))
                .findAll();
        Mockito.verify(examResultMapper, Mockito.times(1))
                .toExamResultDtoList(examResultList);
    }

    @Test
    void getByIdTest() {
        Integer given = 1;

        Mockito.when(examResultRepository.findById(given))
                .thenReturn(Optional.of(examResult));
        Mockito.when(examResultMapper.toExamResultDto(examResult))
                .thenReturn(examResultResponseDto);

        ExamResultResponseDto actual = examResultService.getById(given);

        Assertions.assertEquals(examResultResponseDto, actual);
        Mockito.verify(examResultRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(examResultMapper, Mockito.times(1))
                .toExamResultDto(examResult);
    }

    @Test
    void getByIdNotFoundExamResultTest() {
        Integer given = 100;

        Mockito.when(examResultRepository.findById(given))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ServiceException.class, () -> examResultService.getById(given));
        Mockito.verify(examResultRepository, Mockito.times(1))
                .findById(given);
        Mockito.verify(examResultMapper, Mockito.never())
                .toExamResultDto(examResult);
    }

    @Test
    void deleteTest() {
        Integer given = 1;
        examResultService.delete(given);
        Mockito.verify(examResultRepository, Mockito.times(1))
                .deleteById(given);
    }
}
