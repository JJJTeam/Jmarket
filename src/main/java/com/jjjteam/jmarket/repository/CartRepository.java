package com.jjjteam.jmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjjteam.jmarket.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findByUserId(Long userId);
	
}
