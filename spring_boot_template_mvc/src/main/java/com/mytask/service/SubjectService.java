package com.mytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.repo.CourseRepo;
import com.mytask.repo.SubjectRepo;

@Service
public class SubjectService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private SubjectRepo subjectRepository;

    // Define methods to interact with the SubjectRepository as needed
}