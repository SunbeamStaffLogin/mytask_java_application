package com.mytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    // Add custom query methods if needed
}