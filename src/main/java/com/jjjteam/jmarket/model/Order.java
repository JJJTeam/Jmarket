package com.jjjteam.jmarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.jjjteam.jmarket.constant.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/*
 * 생성한 주문 상품 객체를 이용하여 주문 객체를 만드는 메서드를 작성
 * */


@Getter
@Setter
@Entity
@Table(	name = "orders",
        uniqueConstraints = {
        })
public class Order extends BaseEntity{  // 카멜표기법으로 , db저장은 스네이크 표기법

    @Id
    @GeneratedValue
    private Long id; //주문번호
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;
    
    private LocalDateTime orderDate; //주문일
    
   //주문상태
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    
    /* orderItems에는 주문 상품 정보들을 담아줍니다. 
     * orderItem객체를 order객체를 order객체의 orderItems에 추가
     */
    public void addOrderItem(OrderItem orderItem) {
    	orderItems.add(orderItem);
    	orderItem.setOrder(this);
    }
    
    //상품을 주문한 회원의 정보를 셋팅
    public static Order createOrder(User user, List<OrderItem> orderItemList){
    	Order order = new Order();
    	order.setUser(user);
    	
    	/*
    	 * 상품페이지에서는1개의 상품을 주문하지만, 장바구니 페이지에서는 한 번에 여러개의 상품을 주문할 수있다.
    	 * 따라서 여러개의 상품을 담을 수 있도록 리스트형태로 파라미터 값을 받으며, 주문 객체에 orderItem 객체를 추가
    	 * */
    	for(OrderItem orderItem : orderItemList) {
    		order.addOrderItem(orderItem);
    	}
    	//주문상태를 ORDER, 현재시간을 주문시간으로 세팅 
    	order.setOrderStatus(OrderStatus.ORDER);
    	order.setOrderDate(LocalDateTime.now());
    	return order;
    }
    
    //총 주문 금액을 구하는 메서드
    
    public int getTotalPrice() {
    	int totalPrice = 0;
    	for(OrderItem orderItem : orderItems) {
    		totalPrice += orderItem.getTotalPrice();
    	}
    	return totalPrice;
    }
    
    
    
    
    
}