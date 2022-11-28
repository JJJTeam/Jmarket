package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.Order;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * @Query어노테이션을 이용하여 주문이력을 조회하는 쿼리
 * */
public interface OrderRepository extends JpaRepository<Order, Long> {

//	@Query("select o from Order o" + "where o.memeber.email = :email" + "order by o.orderDate desc")
//
//	//현재 로그인한 사용자의 주문 데이터를 페이징 조건에 맞춰서 조회
//	List<Order> findOrders(@Param("email") String email, Pageable pageable);
//	
//	
//	@Query("select count(o) from order o" + "where o.member.email=:email"
//	)
//	
//	Long countOrder(@Param("email") String email); //현재로그인한 회원의 주문개수가 몇개인지 조회

}