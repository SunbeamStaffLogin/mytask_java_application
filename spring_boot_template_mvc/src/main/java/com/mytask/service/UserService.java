package com.mytask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.repo.CourseRepo;
import com.mytask.repo.SubjectRepo;
import com.mytask.repo.UserRepo;

@Service
public class UserService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private UserRepo userRepository;

    // Define methods to interact with the UserRepository as needed
}