package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddUserDTO;
import com.app.dto.AuthRequest;
import com.app.dto.UserRespDTO;
import com.app.entities.Role;
import com.app.entities.User;
import com.app.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<String> addNewUser(@RequestBody AddUserDTO requestDTO) {

		System.out.println("in add new user " + requestDTO);
		userService.addUesrDetails(requestDTO);
		return ResponseEntity.ok("User Added Successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> signInUser(@RequestBody AuthRequest request) {
		System.out.println("auth req " + request);
		// try {
		UserRespDTO resp = userService.signInUser(request);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/allstaff")
	public List<User> getUsersWithStaffRole() {
		return userService.getUsersByRole(Role.FACULTY);
	}
}
