package com.mytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.repo.CourseRepo;
import com.mytask.repo.EvaluationScheduleRepo;

@Service
public class EvaluationSchemeService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private EvaluationScheduleRepo evaluationScheduleRepo;

    // Define methods to interact with the EvaluationSchemeRepository as needed
}