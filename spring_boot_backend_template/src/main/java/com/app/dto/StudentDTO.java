package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
	private Long id;
	private Long userId;
	private Long courseId;
	private String group;
}
