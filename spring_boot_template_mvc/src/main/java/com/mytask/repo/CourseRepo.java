package com.mytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {

}
