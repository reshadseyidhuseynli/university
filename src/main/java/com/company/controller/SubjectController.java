package com.company.controller;

import com.company.dto.request.QuestionRequestDto;
import com.company.dto.response.QuestionResponseDto;
import com.company.dto.response.SubjectResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectResponseDto> getAll() {
        return subjectService.getAll();
    }

    @GetMapping("/{id}")
    public SubjectResponseDto getById(@PathVariable Integer id) {
        return subjectService.getById(id);
    }

    @GetMapping("/{id}/questions")
    public List<QuestionWithoutAnswerResponseDto> getSubjectQuestionsById(@PathVariable Integer id) {
        return subjectService.getById(id).getQuestions();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        subjectService.delete(id);
    }


    @PostMapping("/{id}/question")
    public QuestionResponseDto addQuestion(@PathVariable("id") Integer subjectId,
                                           @RequestBody QuestionRequestDto requestDto) {
        return subjectService.addQuestionToLesson(subjectId, requestDto);
    }

}
