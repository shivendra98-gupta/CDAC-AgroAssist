package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.LoginRequest;
import com.app.dto.LoginSignUpRequest;
import com.app.service.UserServiceInterface;

@RestController
@RequestMapping("/users")
public class UserController {
    
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired 
	private UserServiceInterface userService;
	
	
	//Method To Register New User : Use By SignUp Page
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody LoginSignUpRequest request)
	{
		
		boolean emailExistOrNot = userService.findUserByEmail(request.getEmail());
		
		if(!emailExistOrNot)
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.userSignUp(request));
		}else {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Email-Id Already Exist !!!!"),HttpStatus.BAD_REQUEST);
		}
	}
	
	//Method To Login The User
	@PostMapping("/login")
	public ResponseEntity<?> loginTheUser(@RequestBody LoginRequest request)
	{
		return ResponseEntity.ok(userService.login(request));
	}
	
	//Update User Account : Available To Both User And Admin
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateUserAccount(@Valid @RequestBody  LoginSignUpRequest userDto,@PathVariable Long userId)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUserDetails(userDto,userId));
	}
	
	//Delete User Account : Available To Both User And Admin
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?>deleteUserAccount(@PathVariable Long userId)
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.deleteUserDetails(userId));
	}
	
	//Get All The User Info : Used Only By Admin 
	@GetMapping("")
	public ResponseEntity<List<LoginSignUpRequest>>getAllUsersDetails()
	{
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	//Get The User Details By Id : Used By Business Logic
	@GetMapping("/{userId}")
	public ResponseEntity<?>getUserDetailsById(@PathVariable Long userId)
	{
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	
	
}
