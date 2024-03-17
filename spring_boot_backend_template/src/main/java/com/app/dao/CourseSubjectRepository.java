package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.dto.CourseSubjectDTO;
import com.app.entities.CourseSubject;
import com.app.entities.Subject;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Long> {
    List<CourseSubject> findAllByCourseId(Long courseId);

	 List<CourseSubject> findSubjectsByCourseId(Long courseId);
	
}