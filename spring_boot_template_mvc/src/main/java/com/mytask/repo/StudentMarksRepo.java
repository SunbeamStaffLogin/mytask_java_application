package com.mytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.Course;
import com.mytask.entities.StudentMarks;

public interface StudentMarksRepo extends JpaRepository<StudentMarks, Long> {

}
