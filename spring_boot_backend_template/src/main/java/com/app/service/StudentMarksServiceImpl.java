package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.StudentMarksRepository;
import com.app.dao.StudentRepository;
import com.app.dao.SubjectRepository;
import com.app.dto.AddStudentMarksDTO;
import com.app.dto.EditStudentMarksDTO;
import com.app.dto.StudentMarksDTO;
import com.app.entities.Student;
import com.app.entities.StudentMarks;
import com.app.entities.Subject;

@Service
@Transactional
public class StudentMarksServiceImpl implements StudentMarksService {
	@Autowired
	private StudentMarksRepository studentObtainMarksRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StudentMarksDTO getStudentObtainMarksById(Long id) {
		StudentMarks studentMarks = studentObtainMarksRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("StudentObtainMarks not found with id: " + id));
//		studentMarks.setSubject(null);
		return modelMapper.map(studentMarks, StudentMarksDTO.class);
	}

	@Override
	public List<StudentMarksDTO> getStudentMarksByStudentId(Long studentId) {
	    List<StudentMarks> studentMarksEntities = studentObtainMarksRepository.findByStudentId(studentId);
	    List<StudentMarksDTO> studentMarksDTOs = new ArrayList<>();

	    for (StudentMarks studentMarksEntity : studentMarksEntities) {
	        StudentMarksDTO studentMarksDTO = new StudentMarksDTO();
	        
	        // Fetch the subject name from the associated Subject entity
	        studentMarksDTO.setSubject(studentMarksEntity.getSubject());
	        
	        // Set other fields
	        studentMarksDTO.setTheoryMarks(studentMarksEntity.getTheoryMarks());
	        studentMarksDTO.setLabMarks(studentMarksEntity.getLabMarks());
	        studentMarksDTO.setIa1Marks(studentMarksEntity.getIa1Marks());
	        studentMarksDTO.setIa2Marks(studentMarksEntity.getIa2Marks());
	        
	        studentMarksDTOs.add(studentMarksDTO);
	    }

	    return studentMarksDTOs;
	}
	@Override
	public StudentMarksDTO saveStudentObtainMarks(AddStudentMarksDTO studentMarksDTO) {
		StudentMarks studObtainedMarks = modelMapper.map(studentMarksDTO, StudentMarks.class);
		Long studentId = studentMarksDTO.getStudentId();
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found")); // Assuming userRepository is your user
																				// repository
		studObtainedMarks.setStudent(student);
		Long subjectId = studentMarksDTO.getSubjectId();
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new RuntimeException("Subject not found"));
		studObtainedMarks.setSubject(subject);
		StudentMarks savestudObtainedMarks = studentObtainMarksRepository.save(studObtainedMarks);

		return modelMapper.map(savestudObtainedMarks,StudentMarksDTO.class);
	}

	@Override
	public StudentMarksDTO editStudentMarks(Long id, EditStudentMarksDTO studentMarksDTO) {
	    // Retrieve the student marks entity from the database
	    StudentMarks studentMarks = studentObtainMarksRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Student marks not found with id: " + id));

	    // Update the student marks entity with data from the DTO
	    if (studentMarksDTO.getSubjectId() != null) {
	        Subject subject = subjectRepository.findById(studentMarksDTO.getSubjectId())
	                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + studentMarksDTO.getSubjectId()));
	        studentMarks.setSubject(subject);
	    }
	    studentMarks.setTheoryMarks(studentMarksDTO.getTheoryMarks());
	    studentMarks.setLabMarks(studentMarksDTO.getLabMarks());
	    studentMarks.setIa1Marks(studentMarksDTO.getIa1Marks());
	    studentMarks.setIa2Marks(studentMarksDTO.getIa2Marks());

	    // Save the updated student marks entity back to the database
	    return modelMapper.map(studentObtainMarksRepository.save(studentMarks), StudentMarksDTO.class);
	}
}
