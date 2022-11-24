package com.jjjteam.jmarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(	name = "orders")
public class Order {  // 카멜표기법으로 , db저장은 스네이크 표기법

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id; //주문번호


    @ManyToOne
    @JoinColumn
    private User user; //고객
    private LocalDateTime orderTime;//주문한 시간
//    private 결제정보테이블

    @ManyToOne
    private UserAddress userAddress;

    @ManyToOne
    @JoinColumn
    private Product product;
    public Order() {

    }
}