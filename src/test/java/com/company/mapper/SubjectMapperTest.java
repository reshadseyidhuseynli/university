package com.company.mapper;

import com.company.dto.request.SubjectRequestDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.entity.Faculty;
import com.company.entity.Question;
import com.company.entity.Subject;
import com.company.entity.Teacher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubjectMapperTest {

    private static final Integer ID = 1;
    private static final String SUBJECT_NAME = "testSubject";

    private static Subject subject;
    private static SubjectResponseDto subjectResponseDto;
    private static SubjectRequestDto subjectRequestDto;
    private static Subject newSubject;

    private final SubjectMapper subjectMapper = SubjectMapper.INSTANCE;

    @BeforeAll
    public static void init() {
        subject = new Subject();
        subject.setId(ID);
        subject.setName(SUBJECT_NAME);
        subject.setFaculty(new Faculty());
        subject.setTeachers(new ArrayList<>(Collections.singletonList(new Teacher())));
        subject.setQuestions(new ArrayList<>(Collections.singletonList(new Question())));

        subjectResponseDto = new SubjectResponseDto();
        subjectResponseDto.setId(ID);
        subjectResponseDto.setName(SUBJECT_NAME);
        subjectResponseDto.setQuestions(new ArrayList<>(Collections
                .singletonList(new QuestionWithoutAnswerResponseDto())));

        subjectRequestDto = new SubjectRequestDto();
        subjectRequestDto.setName(SUBJECT_NAME);

        newSubject = new Subject();
        newSubject.setId(ID);
        newSubject.setName(SUBJECT_NAME);
    }

    @Test
    void toLessonDtoTest() {
        Subject given = subject;
        SubjectResponseDto expected = subjectResponseDto;
        SubjectResponseDto actual = subjectMapper.toSubjectDto(given);
        assertEquals(expected, actual);
    }

    @Test
    void toLessonDtoListTest() {
        List<Subject> given = Collections.singletonList(subject);
        List<SubjectResponseDto> expected = Collections.singletonList(subjectResponseDto);
        List<SubjectResponseDto> actual = subjectMapper.toSubjectDtoList(given);
        assertEquals(expected, actual);
    }

    @Test
    void toLessonTest() {
        SubjectRequestDto given = subjectRequestDto;
        Subject expected = newSubject;

        Subject actual = subjectMapper.toSubject(given);
        actual.setId(ID);
        assertEquals(expected, actual);
    }

    @Test
    void toLessonListTest() {
        List<SubjectRequestDto> given = Collections.singletonList(subjectRequestDto);
        List<Subject> expected = Collections.singletonList(subject);

        List<Subject> actual = subjectMapper.toSubjectList(given);
        actual.get(0).setId(ID);
        assertEquals(expected, actual);
    }
}
