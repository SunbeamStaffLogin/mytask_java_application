package com.app.service;

import java.util.List;

import com.app.dto.AddEvaluationScheduleDTO;
import com.app.dto.EvaluationScheduleDTO;

public interface EvaluationScheduleService {
	EvaluationScheduleDTO saveEvaluationSchedule(AddEvaluationScheduleDTO evaluationSchedule);

	EvaluationScheduleDTO getEvaluationScheduleById(Long id);

	List<EvaluationScheduleDTO> getAllSchedules();

	List<EvaluationScheduleDTO> getMarksEntrySummary(Long subjectId);

	void approveMarksEntryForGroup(Long subjectId, String groupId);

	List<EvaluationScheduleDTO> getEvaluationSchedulesByUserId(Long userId);

}
