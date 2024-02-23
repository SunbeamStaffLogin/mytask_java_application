package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="users")
public class User extends BaseEntity{
	@Column
	private String username;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String mobile;
	
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

}
