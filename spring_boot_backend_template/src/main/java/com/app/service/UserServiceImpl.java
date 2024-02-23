package com.app.service;

import javax.validation.Valid;

import com.app.dto.AddUserDTO;
import com.app.dto.AuthRequest;
import com.app.dto.UserRespDTO;

public class UserServiceImpl implements UserService {

	@Override
	public UserRespDTO addUesrDetails(AddUserDTO user) {
		System.out.println("Request in user service layer" + user);
	}

	@Override
	public UserRespDTO signInUser(@Valid AuthRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
