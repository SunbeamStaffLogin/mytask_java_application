package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.SubjectRepository;
import com.app.dto.AddEvaluationScheduleDTO;
import com.app.dto.CourseDTO;
import com.app.dto.CourseSubjectDTO;
import com.app.dto.EvaluationScheduleDTO;
import com.app.entities.Subject;
import com.app.service.CourseService;
import com.app.service.CourseSubjectService;
import com.app.service.EvaluationScheduleService;
import com.app.service.SubjectService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/coco")
public class CocoController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CourseSubjectService courseSubjectService;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private EvaluationScheduleService evaluationScheduleService;

	@GetMapping("/courses")
	public ResponseEntity<List<CourseDTO>> getAllCourses() {
		List<CourseDTO> courses = courseService.getAllCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping("/courses/{courseId}/subjects")
	public ResponseEntity<List<Subject>> getAllSubjectsForCourse(@PathVariable Long courseId) {
		List<Subject> subjects = courseSubjectService.getAllSubjectsForCourse(courseId);
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}

	 @PostMapping("/allocate-marks-entry-task")
	    public ResponseEntity<String> allocateMarksEntryTask(@RequestBody AddEvaluationScheduleDTO evaluationScheduleDTO) {
	        try {
	            evaluationScheduleService.saveEvaluationSchedule(evaluationScheduleDTO);
	            System.out.print(evaluationScheduleDTO.getValidTill());
	            System.out.println(evaluationScheduleDTO.getGroupvalue());
	            System.out.println(evaluationScheduleDTO.getAssignedUserId());
	            return ResponseEntity.ok("Marks entry task allocated successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("Error allocating marks entry task: " + e.getMessage());
	        }
	    }


	@GetMapping("/courses/{courseId}/subjects/{subjectId}/marks-entry-summary")
	public ResponseEntity<?> getMarksEntrySummary(@PathVariable Long courseId, @PathVariable Long subjectId) {
		List<EvaluationScheduleDTO> marksEntrySummary = evaluationScheduleService.getMarksEntrySummary(subjectId);
		return new ResponseEntity<>(marksEntrySummary, HttpStatus.OK);
	}

	@PostMapping("/courses/{courseId}/subjects/{subjectId}/groups/{groupId}/approve-marks-entry")
	public ResponseEntity<?> approveMarksEntry(@PathVariable Long courseId, @PathVariable Long subjectId,
			@PathVariable String groupId) {
		try {
			// Logic to approve marks entry for the group
			evaluationScheduleService.approveMarksEntryForGroup(subjectId, groupId);
			return new ResponseEntity<>("Marks entry for group " + groupId + " approved successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error approving marks entry for group " + groupId + ": " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
