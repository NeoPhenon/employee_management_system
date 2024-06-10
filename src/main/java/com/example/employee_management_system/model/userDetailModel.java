package com.example.employee_management_system.model;

import java.util.Collection;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

public class userDetailModel {
	
	@NotBlank(message = "User name is required")
	@Getter
	@Setter
	private String username;
	
	@NotBlank( message = "Password is required")
	@Size( min = 6 , message = "password must be at least 6 length long")
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	private boolean enabled;
	
	@NotBlank( message = "authority is required")
	@Getter
	@Setter
	private String authority;
	
}
