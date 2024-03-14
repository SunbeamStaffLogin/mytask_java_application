package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Student;
import com.app.entities.User;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByUser(User user);

}
