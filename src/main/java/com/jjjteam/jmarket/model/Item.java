package com.jjjteam.jmarket.model;

import javax.persistence.*;

import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.dto.ItemFormDTO;

import com.jjjteam.jmarket.exception.OutOfStockException;
import lombok.Getter;
import lombok.ToString;
import lombok.Builder;

@ToString
@Getter
@Table(name="item")
@Entity
public class Item {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id; //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name = "price", nullable = false)
    private int price;  //가격

    @Column(nullable = false)
    private int stockNumber;  //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  //상품 상세설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  //상품 판매 상태

    @Builder
    public Item(String itemNm, int price, int stockNumber, String itemDetail, ItemSellStatus itemSellStatus) {
        this.itemNm = itemNm;
        this.price = price;
        this.stockNumber =stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellStatus = itemSellStatus;
    }
    
    public void updateItem(ItemFormDTO itemFormDto) {
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
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
