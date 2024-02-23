package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.StudentRepository;
import com.app.dto.AddStudentDTO;
import com.app.dto.StudentDTO;
import com.app.entities.Student;

public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;  // Assuming you have a repository for Student entities
    
    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentDTO createStudent(AddStudentDTO addStudentDTO) {
        Student student = modelMapper.map(addStudentDTO, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    public void updateStudent(Long studentId, StudentDTO studentDTO) {
        // Check if the student with given id exists
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        // Map the fields from StudentDTO to existing Student
        modelMapper.map(studentDTO, existingStudent);

        // Save the updated student
        studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long studentId) {
        // Check if the student with given id exists
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        // Delete the student
        studentRepository.delete(existingStudent);
    }
}
