package com.mytask.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mytask.entities.Users;

public interface UserRepo extends JpaRepository<Users, Long> {
    // Add custom query methods if needed
}
