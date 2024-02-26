package com.mytask.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mytask.dto.SubjectDTO;
import com.mytask.dto.UserDto;
import com.mytask.entities.Subject;
import com.mytask.entities.Users;
import com.mytask.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/by-role")
    public List<Users> getUsersByRoleId(@RequestParam("roleId") Long roleId) {
    	System.out.println("In UserController"+roleId);
        return userService.getUsersByRoleId(roleId);
    }
    
    @GetMapping("/allStaff")
    public List<UserDto> getAllStaff() {
    	System.out.println("In UserController");
    	Long staffRoleId=(long) 3;
    	List<Users> users = userService.getAllStaff(staffRoleId);
    	List<UserDto> userDto = users.stream()
    		.map(this::convertToDTO)
    		.collect(Collectors.toList());
        return userDto;
    }
    
    
    private UserDto convertToDTO(Users user) {
    	UserDto userDto = new UserDto();
    	userDto.setId(user.getUid());
    	userDto.setStaffName(user.getUsername());
        // Set other attributes if needed
        return userDto;
    }
}
