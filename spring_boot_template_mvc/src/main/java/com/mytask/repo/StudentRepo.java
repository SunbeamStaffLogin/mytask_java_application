package com.mytask.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mytask.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    
	@Query("SELECT DISTINCT s.grup FROM Student s")
	List<String> findDistinctGrups();
}
