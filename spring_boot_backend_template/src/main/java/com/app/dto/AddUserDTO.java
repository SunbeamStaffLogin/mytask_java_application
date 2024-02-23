package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="password")
public class AddUserDTO {
	private String username;

	private String name;

	private String email;

	private String password;

	private String mobile;
	
	private Long roleIdd;

}
