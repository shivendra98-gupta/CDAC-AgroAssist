package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
    
	Optional<Cart> findById(Long id);
}
