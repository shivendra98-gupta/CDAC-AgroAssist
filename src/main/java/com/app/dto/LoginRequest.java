package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
public class LoginRequest{
	
	@NotBlank(message = "Email cannot be blank or null")
	@Email
	private String email;
	@NotBlank(message = "Password cannot be blank")
	private String password;
}
