package com.mytask.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mytask.entities.CourseSubject;
import com.mytask.entities.Subject;

public interface CourseSubjectRepo extends JpaRepository<CourseSubject, Long> {
	
//	List<CourseSubject> findByCourseCourseId(String course);
	 @Query("SELECT cs.subject FROM CourseSubject cs WHERE cs.course.courseId = :courseId")
	List<Subject> findByCourseCourseId(@Param("courseId") Long courseId);
	 
//	List<CourseSubject> findByCourseCourseId(Long courseId);
}
