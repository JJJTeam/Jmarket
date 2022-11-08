package com.jjjteam.jmarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserAddress {  // 카멜표기법으로 , db저장은 스네이크 표기법
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String addressDetail;
    private String addressPhoneNumber;
    private String person;
    @Column(unique = true)
    private Boolean defaultAddress;

    @ManyToOne
    @JoinColumn(name = "user_table_id")
    private User user;



    public UserAddress() {
    }
}