package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CourseRepository;
import com.app.dao.StudentMarksRepository;
import com.app.dao.StudentRepository;
import com.app.dao.UserRepository;
import com.app.dto.AddStudentDTO;
import com.app.dto.StudentDTO;
import com.app.dto.StudentMarksDTO;
import com.app.entities.Course;
import com.app.entities.Student;
import com.app.entities.StudentMarks;
import com.app.entities.User;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository; // Assuming you have a repository for Student entities

	@Autowired
	private StudentMarksRepository studentMarksRepository; // Assuming you have a repository for Student entities

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<StudentDTO> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		return students.stream().map(student -> modelMapper.map(student, StudentDTO.class))
				.collect(Collectors.toList());
	}

	public StudentDTO getStudentById(Long studentId) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
		return modelMapper.map(student, StudentDTO.class);
	}

	public StudentDTO createStudent(AddStudentDTO addStudentDTO) {
		Student student = modelMapper.map(addStudentDTO, Student.class);
		Long userId = addStudentDTO.getUserId();
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); // Assuming
		student.setUser(user);
		Long courseId = addStudentDTO.getCourseId();
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
		student.setCourse(course);
		student.setGroup(addStudentDTO.getGroup());
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

	@Override
	public Student findByUserId(Long userId) {
		User user = new User();
		user.setId(userId);
		return studentRepository.findByUser(user);
	}

	@Override
	public List<StudentDTO> getAllGroups() {
		return studentRepository.findAll().stream().map(student -> {
			StudentDTO dto = new StudentDTO();
			dto.setId(student.getId());
			dto.setUser(student.getUser()); // Assuming UserDTO is available
			dto.setCourse(student.getCourse()); // Assuming CourseDTO is available
			dto.setGroup(student.getGroup());
			return dto;
		}).collect(Collectors.toList());
	}

	public List<StudentMarksDTO> getAllStudentsMarks() {
        // Fetch student marks from the repository
        List<StudentMarks> studentMarksList = studentMarksRepository.findAll();
        
        // Map the StudentMarks entities to DTOs using ModelMapper
        List<StudentMarksDTO> studentMarksDTOs = studentMarksList.stream()
                .map(studentMarks -> modelMapper.map(studentMarks, StudentMarksDTO.class))
                .collect(Collectors.toList());

        // You can perform any additional processing or validation here if needed

        // Return the list of student marks DTOs
        return studentMarksDTOs;
    }
}
