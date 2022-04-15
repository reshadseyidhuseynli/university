package com.company.database.repository;

import com.company.database.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
