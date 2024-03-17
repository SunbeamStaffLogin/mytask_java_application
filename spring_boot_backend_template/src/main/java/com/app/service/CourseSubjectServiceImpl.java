package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CourseSubjectRepository;
import com.app.dao.EvaluationScheduleRepository;
import com.app.dao.SubjectRepository;
import com.app.dto.EvaluationScheduleDTO;
import com.app.entities.CourseSubject;
import com.app.entities.EvaluationSchedule;
import com.app.entities.Subject;

@Service
@Transactional
public class CourseSubjectServiceImpl implements CourseSubjectService {

	@Autowired
	private CourseSubjectRepository courseSubjectRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private EvaluationScheduleRepository evaluationScheduleRepository;

	@Autowired
	private ModelMapper modelMapper;


    @Override
    public List<Subject> getAllSubjectsForCourse(Long courseId) {
        List<CourseSubject> courseSubjects = courseSubjectRepository.findSubjectsByCourseId(courseId);

        List<Subject> subjects = new ArrayList<>();

        for (CourseSubject courseSubject : courseSubjects) {
            // Assuming that CourseSubject has a reference to Subject entity
            subjects.add(courseSubject.getSubject());
        }

        return subjects;
    }
	@Override
	public void allocateMarksEntryTask(EvaluationScheduleDTO evaluationScheduleDTO) {
//		Subject subject = subjectRepository.findById(evaluationScheduleDTO.getSubject())
//				.orElseThrow(() -> new EntityNotFoundException(
//						"Subject not found with id: " + evaluationScheduleDTO.getSubjectId()));

		// Create a new MarksEntryTask entity
		EvaluationSchedule marksEntryTask = new EvaluationSchedule();
		marksEntryTask.setSubject(evaluationScheduleDTO.getSubject());
		marksEntryTask.setAssignedUser(evaluationScheduleDTO.getAssignedUser());
		marksEntryTask.setEvaluationType(evaluationScheduleDTO.getEvaluationType());
		marksEntryTask.setGroupvalue(evaluationScheduleDTO.getGroupvalue());
		marksEntryTask.setValidTill(evaluationScheduleDTO.getValidTill());

		// Save the marks entry task to the database
		evaluationScheduleRepository.save(marksEntryTask);
	}

	@Override
	public List<EvaluationScheduleDTO> getMarksEntrySummary(Long subjectId) {
		// Retrieve evaluation schedules for the given subjectId
		List<EvaluationSchedule> schedules = evaluationScheduleRepository.findBySubjectId(subjectId);

		// Create a list to store DTOs
		List<EvaluationScheduleDTO> dtos = new ArrayList<>();

		// Map entities to DTOs
		for (EvaluationSchedule schedule : schedules) {
			EvaluationScheduleDTO dto = new EvaluationScheduleDTO();
			Subject subject = subjectRepository.findById(subjectId)
					.orElseThrow(() -> new RuntimeException("Subject not found"));
			dto.setSubject(subject);
			dto.setEvaluationType(schedule.getEvaluationType());
			dto.setValidTill(schedule.getValidTill());
			dto.setGroupvalue(schedule.getGroupvalue());
		//	dto.setAssignedUser(schedule.getAssignedUserId());
			dtos.add(dto);
		}

		return dtos;
	}
	@Override
	public void approveMarksEntry(EvaluationScheduleDTO evaluationScheduleDTO) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void approveMarksEntry(EvaluationScheduleDTO evaluationScheduleDTO) {
//		// Find the evaluation schedules based on subjectId
////		List<EvaluationSchedule> evaluationSchedules = evaluationScheduleRepository
////				.findBySubjectId(evaluationScheduleDTO.getSubject());
//
//		// Update the assignedUserId in each evaluation schedule
//		for (EvaluationSchedule evaluationSchedule : evaluationSchedules) {
//			evaluationSchedule.setAssignedUserId(evaluationScheduleDTO.getAssignedUserId());
//			// Save each updated evaluation schedule
//			evaluationScheduleRepository.save(evaluationSchedule);
//		}
//	}
}
