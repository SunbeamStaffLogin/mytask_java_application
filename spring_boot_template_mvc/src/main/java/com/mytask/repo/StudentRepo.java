package com.mytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    // Add custom query methods if needed
}
