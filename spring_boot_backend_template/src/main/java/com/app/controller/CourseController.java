package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddCourseDTO;
import com.app.dto.CourseDTO;
import com.app.dto.EditCourseDTO;
import com.app.dto.SubjectDTO;
import com.app.service.CourseService;
import com.app.service.SubjectService;

@RestController
@RequestMapping("courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;    
    
    @PostMapping("/add")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody AddCourseDTO courseDTO) {
        CourseDTO addedCourse = courseService.addCourse(courseDTO);
        return new ResponseEntity<>(addedCourse, HttpStatus.CREATED);
    }
    

    @PutMapping("/update/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(
            @PathVariable Long courseId, @RequestBody EditCourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(courseDTO);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        SubjectDTO subject = subjectService.getSubjectById(id);
        if (subject != null) {
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
