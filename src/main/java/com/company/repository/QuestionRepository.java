package com.company.repository;

import com.company.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByIdIn(List<Integer> ids);

    @Query(value = "select * from Question where subject_id = ? order by RAND() limit ?",
            nativeQuery = true)
    List<Question> getRandomQuestion(Integer subjectId, Integer count);

}
