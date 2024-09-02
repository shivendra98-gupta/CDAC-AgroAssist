package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.LoginRequest;
import com.app.dto.LoginResponse;
import com.app.dto.LoginSignUpRequest;
import com.app.entities.Cart;
import com.app.entities.User;
import com.app.exceptions.ResourceNotFoundException;
import com.app.exceptions.UserLoginException;
import com.app.repository.CartRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInterface {
    
	@Autowired 
	private UserRepository userRepo;
	
	//For managing cart : When User Is Created AND When User Is Deleted 
	@Autowired 
	private CartRepository cartRepo;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	
	
	//Implementation Of Login Method : login()
	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		
		User user = userRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
				.orElseThrow(() -> new UserLoginException("No Such User Found !!!"));
		
		//Fill the userLogin DTO with User details through ModelMapper
		LoginResponse userLogin = modelMapper.map(user,LoginResponse.class);
		return userLogin;
		
	}
	
	//Implementation Of Sign Up Method : userSignUp
	@Override 
	public ApiResponse userSignUp(LoginSignUpRequest signUp) 
	{   
		//Here the User is transient 
		User user = modelMapper.map(signUp, User.class);
		
		
		 //Now Creating Cart Entity For Newly Added User (Bi-directional)
		 Cart cart = new Cart();
		 user.setCart(cart); //Helper Method Of User Entity 
		 cartRepo.save(cart);
		
		//Now saving the changes (User is persistent) 
		 user = userRepo.save(user);
		 
		 return new ApiResponse("User registered with ID: "+user.getId()+" Successfully!");
	}
	
	
	//Implementation Of Finding The User By Email : findUserByEmail()
	@Override
	public boolean findUserByEmail(String email) {
		
		Optional<User> user = userRepo.findByEmail(email);
		if(user.isPresent())
		{
			return true;
		}
		 return false;
	}
	
	//Implementation Of Update The User : updateUserDetails()
	@Override 
	public LoginSignUpRequest updateUserDetails(LoginSignUpRequest userDto, Long userId)
	{
		User user = userRepo.findById(userId)
				    .orElseThrow(() -> new ResourceNotFoundException("This User Doesn't Exists In The Database !!!"));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhone(userDto.getPhone());
		user.setRole(userDto.getRole());
		user.setAddress(userDto.getAddress());
		
		User persistUser = userRepo.save(user);
		
		LoginSignUpRequest updateResponse =  modelMapper.map(persistUser, LoginSignUpRequest.class);
		return updateResponse;
    }
	
	
	//Implementation Of Delete The User : deleteUserDetails()
	@Override
	public ApiResponse deleteUserDetails(Long userId)
	{
		User user = userRepo.findById(userId)
			    .orElseThrow(() -> new ResourceNotFoundException("This User Doesn't Exists In The Database !!!"));
		
		userRepo.delete(user);
		
		return new ApiResponse("User with Email : "+user.getEmail()+" Deleted Successfully !!!");
	}
	
	//Implementation Of Get All The User By : getAllUsers()
	@Override
	public List<LoginSignUpRequest> getAllUsers()
	{
		List<User> users = userRepo.findAll();
		List<LoginSignUpRequest> usersDto = users.stream()
				.map(userData -> modelMapper.map(userData, LoginSignUpRequest.class))
				.collect(Collectors.toList());
		
		return usersDto;
	}
	
	
	//Implementation Of Get User By Id : getUserById()
	@Override
	public LoginSignUpRequest getUserById(Long userId)
	{
		User user = userRepo.findById(userId)
			    .orElseThrow(() -> new ResourceNotFoundException("This User Doesn't Exists In The Database !!!"));
		
		LoginSignUpRequest userDto = modelMapper.map(user, LoginSignUpRequest.class);
		
		return userDto;
	}
	
	//Implementation Of Find The User By UserID : findUserById()
	@Override
	public User findUserById(Long userId) {
	  User user = userRepo.findById(userId)
			  .orElseThrow(() -> new ResourceNotFoundException("User with Id "+userId+" Not found"));
		return user;
	}

}
