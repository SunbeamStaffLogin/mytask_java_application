package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.SubjectRepository;
import com.app.dto.SubjectDTO;
import com.app.entities.Subject;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects.stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        return subjectOptional.map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .orElse(null);
    }
	
}
