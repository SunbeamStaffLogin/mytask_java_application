package com.mytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.repo.CourseRepo;

@Service
public class CourseService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private CourseRepo courseRepository;

    // Define methods to interact with the CourseRepository as needed
}