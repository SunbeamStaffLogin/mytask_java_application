package com.mytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.repo.CourseRepo;
import com.mytask.repo.StudentMarksRepo;

@Service
public class MarksEntryService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private StudentMarksRepo studentMarksRepo;

    // Define methods to interact with the MarksEntryRepository as needed
}