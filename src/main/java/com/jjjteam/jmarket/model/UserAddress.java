package com.jjjteam.jmarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserAddress {  // 카멜표기법으로 , db저장은 스네이크 표기법
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postCode;
    private String address;
    private String addressDetail;
    private String addressPhoneNumber;
    private String person;
    private Boolean defaultAddress;

    @ManyToOne
//    @JoinColumn(name = "user_table_id")
    @JoinColumn
    private User user;



    public UserAddress() {
    }
}