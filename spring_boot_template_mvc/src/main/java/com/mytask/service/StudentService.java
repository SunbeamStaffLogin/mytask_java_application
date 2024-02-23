package com.mytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.repo.CourseRepo;
import com.mytask.repo.StudentRepo;

@Service
public class StudentService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private StudentRepo studentRepo;

    // Define methods to interact with the StudentRepository as needed
}