package com.jjjteam.jmarket.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class CartDetailDTO {

    private Long cartItemId; //장바구니 상품 아이디

    private String itemNm; //상품명

    private int price; //상품 금액
    
	
    private int count; //수량
    

    //private String imgUrl; //상품 이미지 경로
    
    @NotBlank(message = "대표이미지는 필수입니다.")
	private String repimg;
    

    public CartDetailDTO(Long cartItemId, String itemNm, int price, int count, String repimg){
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.repimg = repimg;
        //this.imgUrl = imgUrl;
    }

}