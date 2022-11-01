package com.jjjteam.jmarket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userid"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {  // 카멜표기법으로 , db저장은 스네이크 표기법
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String userId;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    private String userName;
    private String userPhoneNumber;
    private byte userSex;                   //성별
    private Date userBirthDate;             //회원생년월일
    private byte userReceiveEmail;          //이메일수신여부
    private byte userReceiveSms;            //문자수신여부
    private byte userSmsCert;               // 문자 인증 여부
    private LocalDate userRegisterDateTime; //회원가입시간
//  private String UserRegisterIp;          //가입 ip

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_address_id")
    private List<UserAddress> userAddresses;

    public User() {
    }
}