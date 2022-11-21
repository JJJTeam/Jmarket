package com.jjjteam.jmarket.model;

import com.jjjteam.jmarket.constant.ItemSellStatus;
import com.jjjteam.jmarket.dto.ItemFormDTO;
import com.jjjteam.jmarket.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product {  // 카멜표기법으로 , db저장은 스네이크 표기법
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id; //상품 코드
    private String productName; //상품명
    private String productMiddleName;  //상품 미들네임
    private String itemSellStatus;  //카테고리
    private int productPrice;  //가격
    private String productColor;  //색상
    private int productSize;  //사이즈
    private int stockNumber;  //재고수량

    public Product() {

    }
}