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

	@Query("select o from Order o " +
            "where o.user.email = :email " +
            "order by o.orderDate desc"
    )
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from Order o " +
            "where o.user.email = :email"
    )
    Long countOrder(@Param("email") String email);

}