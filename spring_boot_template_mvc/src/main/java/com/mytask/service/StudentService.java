package com.mytask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.entities.Student;
import com.mytask.entities.Users;
import com.mytask.repo.CourseRepo;
import com.mytask.repo.StudentRepo;
import com.mytask.repo.UserRepo;

@Service
public class StudentService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private StudentRepo studentRepo;

    public List<String> getAllGroups(){
    	System.out.println("StudentService getAllGroups()");
    	return studentRepo.findDistinctGrups();
    }
}