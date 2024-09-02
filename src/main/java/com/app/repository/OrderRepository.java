package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
       
	  Optional<Order> findById(Long id);
}
