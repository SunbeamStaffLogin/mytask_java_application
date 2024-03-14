package com.app.service;

import java.util.List;

import com.app.dto.AddStudentDTO;
import com.app.dto.StudentDTO;
import com.app.entities.Student;

public interface StudentService {
	List<StudentDTO> getAllStudents();

	StudentDTO getStudentById(Long studentId);

	StudentDTO createStudent(AddStudentDTO studentDTO);

	void updateStudent(Long studentId, StudentDTO studentDTO);

	void deleteStudent(Long studentId);

	Student findByUserId(Long userId);
}
