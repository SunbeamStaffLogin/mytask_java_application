package com.mytask.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytask.entities.Users;
import com.mytask.repo.UserRepo;

@Service
public class UserService {
    
    // Inject any necessary repositories or other dependencies
    @Autowired
    private UserRepo userRepo;
    
    @Transactional
    public List<Users> getUsersByRoleId(Long roleId) {
    	System.out.println("In UserService="+roleId);
    	return userRepo.findByRoleRoleId(roleId);
    }
    
    public List<Users> getAllStaff(Long staffRoleId) {
    	System.out.println("In UserService =getAllStaff");
    	return userRepo.findByRoleRoleId(staffRoleId);
    }
}