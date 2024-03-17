package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEvaluationScheduleDTO {
//	private Long id;
//	@JsonIgnore
	private Long subjectId; // Foreign key reference to Subject
	private String evaluationType; // e.g., "Theory", "Lab", "IA1", "IA2"
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate validTill;
	private String groupvalue;
	private Long assignedUserId;

}
