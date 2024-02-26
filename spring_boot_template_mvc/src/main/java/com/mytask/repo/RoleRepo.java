package com.mytask.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
