package com.jjjteam.jmarket.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItemDTO {

	//회원 한명 다 1개의 장바구니를 가지므로  해당회원의장바구니 생성 
	@NotNull(message= "상품 아이디는 필수 입력 값 입니다.")
	private Long itemId;
	
	@Min(value =1, message="최소 1개 이상 담아주세요.")
	private int count;
	
}
