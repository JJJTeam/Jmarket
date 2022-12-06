package com.jjjteam.jmarket.dto;


import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ItemListDTO {
	
	private Long id;
	
	//private String imgUrl;
	
	private String itemNm;
	
	private Integer price;
	
	
	@QueryProjection // entity 로 원래 바꿨었는데, 이 어노테이션을 쓰면은 dto 로 객체를 변환할 수 있다.
    public ItemListDTO(Long id, String itemNm, Integer price){
        this.id = id;
        this.itemNm = itemNm;
      //  this.imgUrl = imgUrl;
        this.price = price;
    }


}
