
package com.jjjteam.jmarket.model;

import com.jjjteam.jmarket.constant.ClothingItems;
import com.jjjteam.jmarket.constant.ItemColor;
import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.constant.ItemSize;
import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Table(name="item")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item extends BaseTimeEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id; //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명
    
    @Column(nullable = false, length = 200)
    private String itemIntroduction;  //상품 소개 (미들네임)

    @Column(name = "price", nullable = false)
    private int price;  //가격

    @Column(nullable = false)
    private int stockNumber;  //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  //상품 상세설명

    @Lob
    @Column(nullable = false)
    private String repimg;  // 대표이미지

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  //상품 판매상태
    
    @Enumerated(EnumType.STRING)
    private ClothingItems clothingItems;  //상품 분류

    @Enumerated(EnumType.STRING)
    private ItemColor itemColor;	//상품 색상

    @Enumerated(EnumType.STRING)
    private ItemSize itemSize;		//상품 사이즈


    public void updateItem(ItemFormDTO itemFormDto) {
        this.itemNm = itemFormDto.getItemNm();
        this.itemIntroduction = itemFormDto.getItemIntroduction();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
        this.clothingItems = itemFormDto.getClothingItems();
        this.itemColor = itemFormDto.getItemColor();
        this.itemSize = itemFormDto.getItemSize();
        this.repimg = itemFormDto.getRepimg();
    }
	
    // 상품 주문 -> 상품 재고 감소 로직 생성
    public void removeStock(int stockNumber){

        int restStock = this.stockNumber - stockNumber; // stockNumber: 상품의 재고 수량 restStock: 주문 후 남은 재고 수량

        if(restStock<0){ // 상품 재고가 주문 수량보다 작을 경우, 재고 부족 예외 발생
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock; // 주문 후 남은 재고 수량 = 상품의 현재 재고 값
    }

    // 주문 취소 시 상품 개수 늘림
    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }

}