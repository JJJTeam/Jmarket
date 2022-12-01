package com.jjjteam.jmarket.dto;

import com.jjjteam.jmarket.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

//주문이력 조회하기
@Getter
@Setter
public class OrderItemDTO {

	private String itemNm; //상품명
	private int count; //주문수량 
	private int orderPrice; //상품가
	private String imgUrl; //상품이미지경로
	
	
	
	/*
	 * OrderItemDTO 클래스의 생성자로 orderItem객체와 
	 * 이미지 경로를 파라미터로 받아서 멤버변수로 값을 세팅 
	 * */
	public OrderItemDTO(OrderItem orderItem, String imgUrl) {
		this.itemNm = orderItem.getItem().getItemNm();
		this.count = orderItem.getCount();
		this.orderPrice = orderItem.getOrderPrice();
		this.imgUrl =imgUrl;
	}
	
	
}
