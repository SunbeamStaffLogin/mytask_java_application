package com.app.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddStudentDTO;
import com.app.dto.StudentDTO;
import com.app.dto.StudentMarksDTO;
import com.app.entities.Student;
import com.app.service.StudentMarksService;
import com.app.service.StudentService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMarksService studentMarksService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @GetMapping("/findByStudentUserId/{userId}")
    public ResponseEntity<Student> findByUserId(@PathVariable Long userId) {
        Student student = studentService.findByUserId(userId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public StudentDTO getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody AddStudentDTO addStudentDTO) {
        return studentService.createStudent(addStudentDTO);
    }

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable Long studentId, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(studentId, studentDTO);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }
    
    @GetMapping("/marks/{studentId}")
    public ResponseEntity<List<StudentMarksDTO>> getStudentMarks(@PathVariable Long studentId) {
        // Get student marks by studentId
    	System.out.println();
        List<StudentMarksDTO> studentMarks = studentMarksService.getStudentMarksByStudentId(studentId);
        return ResponseEntity.ok(studentMarks);
    }

    @GetMapping("/getAllGroups")
    public ResponseEntity<List<StudentDTO>> getAllGroups() {
        List<StudentDTO> groups = studentService.getAllGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
    
}