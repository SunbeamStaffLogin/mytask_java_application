package com.mytask.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.dto.SubjectDTO;
import com.mytask.entities.Course;
import com.mytask.entities.CourseSubject;
import com.mytask.entities.Subject;
import com.mytask.service.CourseService;
import com.mytask.service.CourseSubjectService;

@CrossOrigin("*")
@RestController
@RequestMapping("/courseSubject")
public class CourseSubjectController {
    
    @Autowired
    private CourseSubjectService courseSubjectService ;

//    @GetMapping("/by-course")
//    public List<CourseSubject> getAllCourses(@RequestParam("course") String course) {
//    	System.out.println("In CourseSubjectController course="+course);
//        return courseSubjectService.getCourseSubjectsByCourseId(course );
//    }
    @GetMapping("/by-course")
    public List<SubjectDTO> getAllCourses(@RequestParam("courseId") Long courseId) {
    	System.out.println("In CourseSubjectController courseId="+courseId);
        List<Subject> subjects=courseSubjectService.getCourseSubjectsByCourseId(courseId );
        //Convert Subject to DTO's
        List<SubjectDTO> subjectDtos=subjects.stream()
        	.map(this::convertToDTO)
        	.collect(Collectors.toList());				
        return subjectDtos;
    }
    private SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setSub_id(subject.getSub_id());
        subjectDTO.setSubject(subject.getSubject());
        // Set other attributes if needed
        return subjectDTO;
    }
//    @GetMapping("/by-course")
//    public List<CourseSubject> getAllCourses(@RequestParam("courseId") Long courseId) {
//    	System.out.println("In CourseSubjectController courseId="+courseId);
//        return courseSubjectService.getCourseSubjectsByCourseId(courseId );
//    }
}