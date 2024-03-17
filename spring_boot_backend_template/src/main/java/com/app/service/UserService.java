package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.AddUserDTO;
import com.app.dto.AuthRequest;
import com.app.dto.UserRespDTO;
import com.app.entities.Role;
import com.app.entities.User;

public interface UserService {
	UserRespDTO addUesrDetails(AddUserDTO user);

	UserRespDTO signInUser(@Valid AuthRequest request);

	UserRespDTO getUserById(Long id);

	List<User> getUsersByRole(Role role);
}
