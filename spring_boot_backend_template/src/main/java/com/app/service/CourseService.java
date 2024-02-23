package com.app.service;

import java.util.List;

import com.app.dto.AddCourseDTO;
import com.app.dto.CourseDTO;
import com.app.dto.EditCourseDTO;

public interface CourseService {
	CourseDTO addCourse(AddCourseDTO courseDTO);

	CourseDTO updateCourse(EditCourseDTO courseDTO);

	void deleteCourse(Long courseId);

	List<CourseDTO> getAllCourses();
}
