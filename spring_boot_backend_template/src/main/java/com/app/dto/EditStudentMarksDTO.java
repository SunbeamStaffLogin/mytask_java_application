package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditStudentMarksDTO {
		private Long studentId; // Foreign key reference to User
		private Long subjectId; // Foreign key reference to Subject
	    private int theoryMarks;
	    private int labMarks;
	    private int ia1Marks;
	    private int ia2Marks;
	}
