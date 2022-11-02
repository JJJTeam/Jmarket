package com.jjjteam.jmarket.dto;

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
}
