package com.app.service;

import java.util.List;

import com.app.dto.SubjectDTO;

public interface SubjectService {
	List<SubjectDTO> getAllSubjects();

	SubjectDTO getSubjectById(Long id);

}
