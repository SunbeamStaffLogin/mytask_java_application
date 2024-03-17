package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddStudentMarksDTO;
import com.app.dto.EditStudentMarksDTO;
import com.app.dto.EvaluationScheduleDTO;
import com.app.dto.StudentDTO;
import com.app.dto.StudentMarksDTO;
import com.app.dto.SubjectDTO;
import com.app.service.EvaluationScheduleService;
import com.app.service.StudentMarksService;
import com.app.service.StudentService;
import com.app.service.SubjectService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StudentMarksService studentMarksService;

	@Autowired
	private EvaluationScheduleService evaluationScheduleService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private StudentService studentService;

	@GetMapping("/students")
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		// Get all students
		List<StudentDTO> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}

	@GetMapping("/subjects")
	public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
		// Get all subjects
		List<SubjectDTO> subjects = subjectService.getAllSubjects();
		return ResponseEntity.ok(subjects);
	}

	@GetMapping("/evaluationSchedules")
	public ResponseEntity<List<EvaluationScheduleDTO>> getEvaluationSchedules() {
		// Get evaluation schedules
		List<EvaluationScheduleDTO> schedules = evaluationScheduleService.getAllSchedules();
		return ResponseEntity.ok(schedules);
	}

	@PostMapping("/enterMarks")
	public ResponseEntity<String> enterMarks(@RequestBody AddStudentMarksDTO studentMarksDTO) {
		try {
			studentMarksService.saveStudentObtainMarks(studentMarksDTO);
			return ResponseEntity.ok("Marks entered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error entering marks: " + e.getMessage());
		}
	}

	@PutMapping("/editMarks/{id}")
	public ResponseEntity<String> editMarks(@PathVariable Long id, @RequestBody EditStudentMarksDTO studentMarksDTO) {
		try {
			studentMarksService.editStudentMarks(id, studentMarksDTO);
			return ResponseEntity.ok("Marks updated successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error updating marks: " + e.getMessage());
		}
	}

	@GetMapping("/marks/studentMarks")
	public ResponseEntity<List<StudentMarksDTO>> getAllStudentsMarks() {
		// Get all students
		List<StudentMarksDTO> students = studentService.getAllStudentsMarks();
		return ResponseEntity.ok(students);
	}
	
	 @GetMapping("/staff/marks/studentMarks/{studentId}")
	    public ResponseEntity<StudentMarksDTO> getStudentMarks(@PathVariable Long studentId) {
	        StudentMarksDTO studentMarks = studentMarksService.getStudentObtainMarksById(studentId);
	        if (studentMarks != null) {
	            return new ResponseEntity<>(studentMarks, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	@GetMapping("/evaluation-schedules/{userId}")
	public List<EvaluationScheduleDTO> getEvaluationSchedulesByUserId(@PathVariable Long userId) {
		return evaluationScheduleService.getEvaluationSchedulesByUserId(userId);
	}
}
