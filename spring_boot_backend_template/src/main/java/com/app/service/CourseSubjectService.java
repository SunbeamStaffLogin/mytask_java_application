package com.app.service;

import java.util.List;

import com.app.dto.CourseSubjectDTO;
import com.app.dto.EvaluationScheduleDTO;
import com.app.dto.SubjectDTO;
import com.app.entities.Subject;

public interface CourseSubjectService {
	  public List<Subject> getAllSubjectsForCourse(Long courseId);

    void allocateMarksEntryTask(EvaluationScheduleDTO evaluationScheduleDTO);
	
	List<EvaluationScheduleDTO> getMarksEntrySummary(Long subjectId);

    void approveMarksEntry(EvaluationScheduleDTO evaluationScheduleDTO);



}
