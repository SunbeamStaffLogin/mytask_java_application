package com.app.dto;

import com.app.entities.Student;
import com.app.entities.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentMarksDTO {
	private Long id;
	private Student student; // Foreign key reference to User
	private Subject subject;	
	private int theoryMarks;
	private int labMarks;
	private int ia1Marks;
	private int ia2Marks;

	
}
