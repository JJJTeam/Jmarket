package com.jjjteam.jmarket.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;


//메인 페이지에서 상품을 보여줄 때 사용할 dto

@Getter
@Setter
public class MainItemDTO {
	
	private Long id;

    private String itemNm;
    private String category;

    private String itemDetail;

    private String imgUrl;
    private Integer price;
	
    
    @QueryProjection
    public MainItemDTO(Long id, String itemNm, String category, String itemDetail, String imgUrl, Integer price) {
		super();
		this.id = id;
		this.itemNm = itemNm;
		this.category = category;
		this.itemDetail = itemDetail;
		this.imgUrl = imgUrl;
		this.price = price;
	}

}
