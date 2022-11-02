package com.jjjteam.jmarket.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.entity.Item;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemFormDTO {

	private Long id;

	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemNm;

	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;

	@NotBlank(message = "상세 내용은 필수 입력 값입니다.")
	private String itemDetail;

	@NotNull(message = "재고는 필수 입력 값입니다.")
	private Integer stockNumber;

	private ItemSellStatus itemSellStatus;
	
	// 상품 저장 후 수정할 때 상품 이미지 정보를 저장하는 리스트
	private List<ItemImgDTO> itemImgDtoList = new ArrayList<>();
	
	// 상품의 이미지 아이디를 저장하는 리스트
    // 상품 등록 전에는 이미지가 없으니까 비어있음(이미지도 공백, 아이디도 공백!)
    // 그냥 수정할 때 이미지 아이디 저장해둘 용도
	private List<Long> itemImgIds = new ArrayList<>();

	@Builder
	public ItemFormDTO(String itemNm, Integer price, String itemDetail, Integer stockNumber,
			ItemSellStatus itemSellStatus) {
		this.itemNm = itemNm;
		this.price = price;
		this.itemDetail = itemDetail;
		this.stockNumber = stockNumber;
		this.itemSellStatus = itemSellStatus;
	}

	public Item toEntity(ItemFormDTO dto) {
		Item entity = Item.builder()
				.itemNm(dto.itemNm)
				.itemDetail(dto.itemDetail)
				.itemSellStatus(dto.itemSellStatus)
				.price(dto.price)
				.stockNumber(dto.stockNumber)
				.build();

		return entity;
	}

	public static ItemFormDTO of(Item entity) {
		ItemFormDTO dto = ItemFormDTO.builder()
				.itemNm(entity.getItemNm())
				.itemDetail(entity.getItemDetail())
				.itemSellStatus(entity.getItemSellStatus())
				.price(entity.getPrice())
				.stockNumber(entity.getStockNumber())
				.build();

		return dto;
	}
}
