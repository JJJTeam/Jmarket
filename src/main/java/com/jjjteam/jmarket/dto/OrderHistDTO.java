package com.jjjteam.jmarket.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.jjjteam.jmarket.constant.OrderStatus;
import com.jjjteam.jmarket.model.Order;

import lombok.Getter;
import lombok.Setter;

/*
 * 주문정보를 담을 DTO
 * 
 */

@Getter
@Setter
public class OrderHistDTO {
	
	private Long orderId; //주문아이
	private String orderDate; //주문 날짜 
	private OrderStatus orderStatus; //주문 상태 
	
	//주문상품리스트
	private List<OrderItemDTO> orderItemDTOList =new ArrayList<>();
	
	
	//OrderHistDTO 클래스의 생성자로 order 객체를 파라미터로 받아서 멤버 변수 값을 세팅합니다.
	//주문날짜의 경우 화면에 "yyyy-mm-dd HH:mm" 형태로 전달하기 위해 포맷을 수정
	public OrderHistDTO(Order order) {
		this.orderId = order.getId();
		this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.orderStatus= order.getOrderStatus();
	}
	
	//orderItemDTO 객체를 주문 상품 리스트에 추가하는 메서드 
	public void addOrderItemDTO(OrderItemDTO orderItemDTO) {
		orderItemDTOList.add(orderItemDTO);
	}
	
}
