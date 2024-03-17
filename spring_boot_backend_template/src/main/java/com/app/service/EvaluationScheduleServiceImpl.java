package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.EvaluationScheduleRepository;
import com.app.dao.SubjectRepository;
import com.app.dao.UserRepository;
import com.app.dto.AddEvaluationScheduleDTO;
import com.app.dto.EvaluationScheduleDTO;
import com.app.entities.EvaluationSchedule;
import com.app.entities.Subject;
import com.app.entities.User;

@Service
@Transactional
public class EvaluationScheduleServiceImpl implements EvaluationScheduleService {

	@Autowired
	private EvaluationScheduleRepository evaluationScheduleRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EvaluationScheduleDTO saveEvaluationSchedule(AddEvaluationScheduleDTO evaluationScheduleDTO) {
		// Map DTO to entity
		EvaluationSchedule evaluationSchedule = modelMapper.map(evaluationScheduleDTO, EvaluationSchedule.class);

		// Fetch or create Subject entity based on subjectId in DTO
		Long subjectId = evaluationScheduleDTO.getSubjectId();
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new RuntimeException("Subject not found"));

		// Fetch or create User entity based on assignedUserId in DTO
		Long assignedUserId = evaluationScheduleDTO.getAssignedUserId();

		User user = userRepository.findById(assignedUserId).orElseThrow(() -> new RuntimeException("User not found"));

		// Set the User entity in the EvaluationSchedule entity
		evaluationSchedule.setAssignedUser(user);

		// Set the Subject entity in the EvaluationSchedule entity
		evaluationSchedule.setSubject(subject);
		// Save the EvaluationSchedule entity
		EvaluationSchedule persistedSchedule = evaluationScheduleRepository.save(evaluationSchedule);

		// Map the persisted entity back to DTO and return
		return modelMapper.map(persistedSchedule, EvaluationScheduleDTO.class);
	}

	@Override
	public EvaluationScheduleDTO getEvaluationScheduleById(Long id) {
		Optional<EvaluationSchedule> optionalEvaluationSchedule = evaluationScheduleRepository.findById(id);
		if (optionalEvaluationSchedule.isPresent()) {
			EvaluationSchedule evaluationSchedule = optionalEvaluationSchedule.get();
			return modelMapper.map(evaluationSchedule, EvaluationScheduleDTO.class);
		} else {
			return null; // or throw an exception, depending on your application's logic
		}
	}

	@Override
	public List<EvaluationScheduleDTO> getAllSchedules() {
		List<EvaluationSchedule> schedules = evaluationScheduleRepository.findAll();
		return schedules.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private EvaluationScheduleDTO convertToDto(EvaluationSchedule schedule) {
		return modelMapper.map(schedule, EvaluationScheduleDTO.class);
	}

	@Override
	public void approveMarksEntryForGroup(Long subjectId, String groupId) {
		// Retrieve evaluation schedules for the specified subject and group
		List<EvaluationSchedule> schedules = evaluationScheduleRepository.findBySubjectIdAndGroupvalue(subjectId,
				groupId);

		// Implement your logic to approve marks entry for the group
		for (EvaluationSchedule schedule : schedules) {
			// Update the status or flag to mark the marks entry as approved
			schedule.setApproved(true); // Assuming there is a boolean field 'approved' in the EvaluationSchedule entity
			// You might perform other operations such as updating timestamps, etc.
		}

		// Save the updated schedules in the database
		evaluationScheduleRepository.saveAll(schedules);
	}

	@Override
	public List<EvaluationScheduleDTO> getMarksEntrySummary(Long subjectId) {
		// Fetch evaluation schedules from repository based on subjectId
		List<EvaluationSchedule> evaluationSchedules = evaluationScheduleRepository.findBySubjectId(subjectId);

		// Map the entities to DTOs
		List<EvaluationScheduleDTO> evaluationScheduleDTOs = evaluationSchedules.stream()
				.map(evaluationSchedule -> modelMapper.map(evaluationSchedule, EvaluationScheduleDTO.class))
				.collect(Collectors.toList());

		return evaluationScheduleDTOs;
	}

	@Override
	public List<EvaluationScheduleDTO> getEvaluationSchedulesByUserId(Long userId) {
		// Convert entities to DTOs if needed
		List<EvaluationSchedule> evaluationSchedules = evaluationScheduleRepository.findByAssignedUserId(userId);

		// List to hold DTOs
		List<EvaluationScheduleDTO> evaluationScheduleDTOs = new ArrayList<>();

		// Convert entities to DTOs
		for (EvaluationSchedule evaluationSchedule : evaluationSchedules) {
			EvaluationScheduleDTO evaluationScheduleDTO = new EvaluationScheduleDTO();

			// Set DTO properties
			evaluationScheduleDTO.setId(evaluationSchedule.getId());
			evaluationScheduleDTO.setSubject(evaluationSchedule.getSubject());
			evaluationScheduleDTO.setEvaluationType(evaluationSchedule.getEvaluationType());
			evaluationScheduleDTO.setValidTill(evaluationSchedule.getValidTill());
			evaluationScheduleDTO.setGroupvalue(evaluationSchedule.getGroupvalue());
			evaluationScheduleDTO.setAssignedUser(evaluationSchedule.getAssignedUser());

			// Add DTO to the list
			evaluationScheduleDTOs.add(evaluationScheduleDTO);
		}

		// Return list of EvaluationScheduleDTOs
		return evaluationScheduleDTOs;
	}
}
