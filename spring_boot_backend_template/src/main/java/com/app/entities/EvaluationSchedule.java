package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "evaluation_schedule")
public class EvaluationSchedule extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "subject_id", nullable = false)
	private Subject subject; // Foreign key reference to Subject
	private String evaluationType; // e.g., "Theory", "Lab", "IA1", "IA2"
	private LocalDate validTill;
	private String groupvalue;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User assignedUser;

	// Setter method to set validTill with only the date part
	public void setApproved(boolean b) {

	}

	// Setter method to set validTill with only the date part
	public void setValidTill(LocalDate date) {
		this.validTill = date; // Set time to midnight
	}

}
