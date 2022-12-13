package com.jjjteam.jmarket.dto;


import javax.persistence.Column;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ItemListDTO {
	
	private Long id;
	
	private String repimg;
	
	private String itemNm;
	
	private Integer price;
	
	private String itemMaterial;
  	private String itemWashing;
  	private String itemFabric;
  	
  	
    
	
	//String itemMaterial,String itemWashing , String itemFabric
	@QueryProjection // entity 로 원래 바꿨었는데, 이 어노테이션을 쓰면은 dto 로 객체를 변환할 수 있다.
    public ItemListDTO(Long id, String itemNm, String repimg, Integer price,String itemMaterial,String itemWashing,String itemFabric){
        this.id = id;
        this.itemNm = itemNm;
        this.repimg = repimg;
        this.price = price;
        this.itemMaterial =itemMaterial;
        this.itemWashing = itemWashing;
        this.itemFabric = itemFabric;
    }


}
