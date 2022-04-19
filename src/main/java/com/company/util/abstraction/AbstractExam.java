package com.company.util.abstraction;

import com.company.database.entity.Question;
import com.company.database.entity.Student;
import com.company.database.entity.Teacher;
import com.company.dto.*;

import java.util.*;

public abstract class AbstractExam implements Exam {
    protected Student student;
    protected Teacher teacher;
    protected List<Question> questionList;
    protected List<Question> examinationPaper;
    protected ResultPaperDTO resultDTO;
    protected final Random random = new Random();

    protected AbstractExam(Student student, Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
        this.questionList = teacher.getLesson().getQuestions();
        this.examinationPaper = getExaminationPaper();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question q : examinationPaper){
            questionDTOS.add(new QuestionDTO(q));
        }
        this.resultDTO = new ResultPaperDTO(new StudentDTO(student), new TeacherDTO(teacher), new LessonDTO(teacher.getLesson()), questionDTOS);
    }

    private List<Question> getExaminationPaper(){
        int questionCount;
        if (questionList.size() < 10)
            questionCount = questionList.size();
        else
            questionCount = 10;

        Set<Integer> randomNumbers = new HashSet<>();
        do {
            int number = random.nextInt(questionList.size()-1);
            randomNumbers.add(number);
        } while (randomNumbers.size() != questionCount);
        List<Integer> randomNumberList = new ArrayList<>(randomNumbers);

        List<Question> selectedQuestions = new ArrayList<>();
        for (int i = 0; i < questionCount; i++){
            selectedQuestions.add(questionList.get(randomNumberList.get(i)));
        }

        return selectedQuestions;
    }
}
