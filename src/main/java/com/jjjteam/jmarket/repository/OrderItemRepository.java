package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
