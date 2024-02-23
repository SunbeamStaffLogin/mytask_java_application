package com.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.app.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
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
}