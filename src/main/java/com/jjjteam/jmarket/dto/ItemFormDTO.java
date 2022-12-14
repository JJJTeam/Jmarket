package com.jjjteam.jmarket.dto;


import com.jjjteam.jmarket.constant.ClothingItems;
import com.jjjteam.jmarket.constant.ItemColor;
import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.constant.ItemSize;
import com.jjjteam.jmarket.model.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter 
@Setter
@ToString
public class ItemFormDTO {

	private Long id;

	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemNm;
	
	@NotBlank(message = "상품설명은 필수 입력 값입니다.")
	private String itemIntroduction;
	
	//추가
	private String itemMaterial;
	
	//추가
	private String itemWashing;
	
	//추가
	private String itemFabric;
	
	

	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;

	@NotBlank(message = "상세 내용은 필수 입력 값입니다.")
	private String itemDetail;

	@NotNull(message = "재고는 필수 입력 값입니다.")
	private Integer stockNumber;

	
	@NotBlank(message = "대표이미지는 필수입니다.")
	private String repimg;
	
	private ItemSellStatus itemSellStatus;
	
	private ClothingItems clothingItems;

	private ItemSize itemSize;

	private ItemColor itemColor;
	
    
    private LocalDateTime regTime;

    private LocalDateTime updateTime;
    

	// 상품의 이미지 아이디를 저장하는 리스트
    // 상품 등록 전에는 이미지가 없으니까 비어있음(이미지도 공백, 아이디도 공백!)
    // 그냥 수정할 때 이미지 아이디 저장해둘 용도
	

	private static ModelMapper modelMapper = new ModelMapper();
    public ItemFormDTO setRepTime(){
        this.regTime = LocalDateTime.now();
        return this;
    }
	
	public Item createItem() {
		return modelMapper.map(this,  Item.class);
	}
	
	public static ItemFormDTO of(Item item) {
		return modelMapper.map(item, ItemFormDTO.class);
	}
	
}
