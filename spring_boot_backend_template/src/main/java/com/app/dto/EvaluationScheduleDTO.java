package com.app.dto;

import java.time.LocalDate;

import com.app.entities.Subject;
import com.app.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationScheduleDTO {
	private Long id;
	//@JsonIgnore
    private Subject subject; // Foreign key reference to Subject
	private String evaluationType; // e.g., "Theory", "Lab", "IA1", "IA2"
	private LocalDate validTill;
	private String groupvalue;
    private User assignedUser; // Change assignedUserId to assignedUser of type User


}
