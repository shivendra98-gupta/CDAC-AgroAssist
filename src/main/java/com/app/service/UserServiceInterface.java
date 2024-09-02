package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.LoginRequest;
import com.app.dto.LoginResponse;
import com.app.dto.LoginSignUpRequest;
import com.app.entities.User;

public interface UserServiceInterface {
    
	//Method For User Login 
	LoginResponse login(LoginRequest loginRequest);
	
	//Method For User SignUp
	ApiResponse userSignUp(LoginSignUpRequest signUp);
	
	//To Check If Duplicate Email Entry Exist Or Not
	boolean findUserByEmail(String email);
	
	//Method To Update The User Account
	LoginSignUpRequest updateUserDetails(LoginSignUpRequest userDto, Long userId);
	
	//Method To Delete The User Account
	ApiResponse deleteUserDetails(Long userId);
	
	//Method To Get All The User Info 
	List<LoginSignUpRequest> getAllUsers();
	
	//Method To Get User By Id 
	LoginSignUpRequest getUserById(Long userId);
	
    //Method Used By Cart To Get User Entity
	 User findUserById(Long userId);
	
}
