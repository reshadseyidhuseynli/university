package com.company.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExamResultResponseDto {

    private Integer id;
    private StudentResponseDto student;
    private TeacherResponseDto teacher;
    private LessonResponseDto lesson;
    private Integer trueAnswerCount;
    private Integer falseAnswerCount;

    public ExamResultResponseDto(StudentResponseDto student,
                                 TeacherResponseDto teacher,
                                 Integer trueAnswerCount,
                                 Integer falseAnswerCount) {

        this.student = student;
        this.teacher = teacher;
        this.lesson = teacher.getLesson();
        this.trueAnswerCount = trueAnswerCount;
        this.falseAnswerCount = falseAnswerCount;
    }

}
