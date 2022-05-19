package com.company.repository;

import com.company.entity.TeacherStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherStudentRepository extends JpaRepository<TeacherStudent, Integer> {
}
