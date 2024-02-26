package com.mytask.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.entities.Course;
import com.mytask.repo.CourseRepo;

@Service
public class CourseService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private CourseRepo courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}