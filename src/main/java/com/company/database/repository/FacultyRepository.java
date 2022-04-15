package com.company.database.repository;

import com.company.database.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
