package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginResponse{
    
	//User Id for session 
	private Long id;
	
	private String name;
	
	//Role for forwarding to specific dashboard
	private String role;
	
	//To save for order etc
	private String address;
}
