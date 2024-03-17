package com.app.dto;

import com.app.entities.Course;
import com.app.entities.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CourseSubjectDTO {
	private Long id;
	@JsonIgnore
	private Course course;
    private Subject subject;
}
