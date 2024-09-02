package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
   
	//JPA Derived method To Get User From Username and Password : For Login
	 Optional<User> findByEmailAndPassword(String email, String password);
   
    //To Check If Duplicate Email Entry Exist Or Not
	 Optional<User> findByEmail(String email);
}
