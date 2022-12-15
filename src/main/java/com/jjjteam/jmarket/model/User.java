package com.jjjteam.jmarket.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userid"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@Entity
public class User {  // 카멜표기법으로 , db저장은 스네이크 표기법
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String email;
    private String password;
    private String userName;
    private String userPhoneNumber;
    private byte userSex;                   //성별
    private LocalDate userBirthDate;             //회원생년월일
    private Boolean userReceiveEmail;          //이메일수신여부
    private Boolean userReceiveSms;            //문자수신여부
    private Boolean userSmsCert;               // 문자 인증 여부
    private LocalDateTime userRegisterDateTime; //회원가입시간
//  private String UserRegisterIp;          //가입 ip
    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
//    @Builder.Default
    private List<UserAddress> userAddresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
//    @Builder.Default
    private List<Order> order = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<CartItem> cartItems = new ArrayList<>();


    public User() {

    }
}