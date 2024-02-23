package com.mytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.CourseSubject;

public interface CourseSubjectRepo extends JpaRepository<CourseSubject, Long> {

}
