package com.jjjteam.jmarket.repository;

import com.jjjteam.jmarket.model.OrderOld;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderOld, Long> {


//    @Query("select o from Order o " +
//            "where o.member.email = :email " +
//            "order by o.orderDate desc")
//    List<Order> findOrders(@Param("email") String email, Pageable pageable);// 현재 로그인한 사용자의 주문 데이터를 조회함 (위의 조건에 맞춰서)
//    List<Order> findByEmail(String email);
//
//    @Query("select count(o) from Order o " +
//            "where o.member.email = :email")
//    Long countOrder(@Param("email") String email); // 현재 로그인한 사용자의 주문 개수를 조회함 (위의 조건에 맞춰서)

}
