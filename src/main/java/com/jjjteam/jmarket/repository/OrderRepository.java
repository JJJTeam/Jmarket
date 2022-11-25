package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @Query어노테이션을 이용하여 주문이력을 조회하는 쿼리
 * */
public interface OrderRepository extends JpaRepository<Order, Long> {

	

}
