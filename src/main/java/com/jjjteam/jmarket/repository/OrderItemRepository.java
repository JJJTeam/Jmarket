package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
