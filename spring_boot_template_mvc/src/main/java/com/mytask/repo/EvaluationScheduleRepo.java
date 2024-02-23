package com.mytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.EvaluationSchedule;

public interface EvaluationScheduleRepo extends JpaRepository<EvaluationSchedule, Long> {
    // Add custom query methods if needed
}