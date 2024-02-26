package com.mytask.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
	
	List<Users> findByRoleRoleId(Long roleId);
}
