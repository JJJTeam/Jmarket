package com.jjjteam.jmarket.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class CartDetailDTO {

	private Long cartItemId; // 장바구니 상품 아이디
	private String itemNm; // 상품명
	private int price; // 상품 금액
	private int count; // 수량
	private String repimg;

//	private String itemMaterial;
//	private String itemWashing;
//	private String itemFabric;

	// private String imgUrl; //상품 이미지 경로

	public CartDetailDTO(Long cartItemId, String itemNm, int price, int count, String repimg ) {
		this.cartItemId = cartItemId;
		this.itemNm = itemNm;
		this.price = price;
		this.count = count;
		this.repimg = repimg;
//		this.itemMaterial = itemMaterial;
//		this.itemWashing = itemWashing;
//		this.itemFabric = itemFabric;

		// this.imgUrl = imgUrl;
	}

}