package com.company.service;

import com.company.dto.request.ExamAnswerRequestDto;
import com.company.dto.response.ExamResultResponseDto;
import com.company.dto.response.QuestionWithoutAnswerResponseDto;
import com.company.entity.Question;
import com.company.entity.Student;
import com.company.entity.Teacher;
import com.company.error.ErrorCode;
import com.company.error.ServiceException;
import com.company.mapper.QuestionWithoutAnswerMapper;
import com.company.mapper.StudentMapper;
import com.company.mapper.TeacherMapper;
import com.company.repository.QuestionRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ExamService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final QuestionRepository questionRepository;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;
    private final QuestionWithoutAnswerMapper questionWithoutAnswerMapper;
    private final Random random;

    public ExamService(StudentRepository studentRepository,
                       TeacherRepository teacherRepository,
                       QuestionRepository questionRepository,
                       StudentMapper studentMapper,
                       TeacherMapper teacherMapper,
                       QuestionWithoutAnswerMapper questionWithoutAnswerMapper) {

        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.questionRepository = questionRepository;
        this.questionWithoutAnswerMapper = questionWithoutAnswerMapper;
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
        random = new Random();

    }

    public List<QuestionWithoutAnswerResponseDto> getExaminationPaperByTeacherId(Integer id) {

        final Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.TEACHER_NOT_FOUND,
                        "Teacher not found by :" + id));

        List<Question> allQuestionsInLesson = teacher.getLesson().getQuestions();
        int allQuestionsCount = allQuestionsInLesson.size();

        int questionCountInExaminationPaper = Math.min(allQuestionsCount, 10);

        Set<Integer> randomNumbers = new HashSet<>();
        do
            randomNumbers.add(random.nextInt(allQuestionsCount));
        while (randomNumbers.size() != questionCountInExaminationPaper);
        List<Integer> questionsNumberInExaminationPaper = new ArrayList<>(randomNumbers);

        List<Question> questions = new ArrayList<>();
        for (Integer number : questionsNumberInExaminationPaper)
            questions.add(allQuestionsInLesson.get(number));


        return questionWithoutAnswerMapper.toQuestionWithoutAnswerDtoList(questions);

    }

    public ExamResultResponseDto acceptAnswersAndGiveResult(ExamAnswerRequestDto requestDto) {

        Integer studentId = requestDto.getStudentId();
        Integer teacherId = requestDto.getTeacherId();
        Integer[] questionsId = requestDto.getQuestionsId();
        Integer[] answers = requestDto.getAnswers();

        if (questionsId.length != answers.length) {
            throw new ServiceException(
                    ErrorCode.THE_COUNT_OF_ANSWERS_IS_INCORRECT,
                    "The count of answers is incorrect");
        }
        final int questionCountInExaminationPaper = questionsId.length;

        final Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.STUDENT_NOT_FOUND,
                        "Student not found by :" + studentId));


        final Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ServiceException(
                        ErrorCode.TEACHER_NOT_FOUND,
                        "Teacher not found by :" + teacherId));

        final List<Question> questions = getQuestionListById(questionsId);

        int trueAnswerCount = 0;
        int falseAnswerCount = 0;

        for (int i = 0; i < questionCountInExaminationPaper; i++) {

            if (questions.get(i).getTrueOption().equals(answers[i]))
                trueAnswerCount++;
            else
                falseAnswerCount++;
        }

        return new ExamResultResponseDto(
                studentMapper.toStudentDto(student),
                teacherMapper.toTeacherDto(teacher),
                trueAnswerCount,
                falseAnswerCount);

    }

    private List<Question> getQuestionListById(Integer[] questionsId){

        List<Question> questions = new ArrayList<>();
        for (Integer questionId : questionsId) {
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new ServiceException(
                            ErrorCode.QUESTION_NOT_FOUND,
                            "Question not found by " + questionId));

            questions.add(question);
        }

        return questions;
    }

}
