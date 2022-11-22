package com.jjjteam.jmarket.model;


import com.jjjteam.jmarket.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;



@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "userid"),
                @UniqueConstraint(columnNames = "email")
        })
@Getter
@Setter
public class User {  // 카멜표기법으로 , db저장은 스네이크 표기법
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String email;
    private String password;
    private String userName;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    private String userPhoneNumber;
    private byte userSex;                   //성별
    private LocalDate userBirthDate;             //회원생년월일
    private Boolean userReceiveEmail;          //이메일수신여부
    private Boolean userReceiveSms;            //문자수신여부
    private Boolean userSmsCert;               // 문자 인증 여부
    private LocalDateTime userRegisterDateTime; //회원가입시간
//  private String UserRegisterIp;          //가입 ip

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    @Builder.Default
    private List<UserAddress> userAddresses = new ArrayList<>();


    @Builder(builderClassName = "SaveByBuilder", builderMethodName = "saveByBuilder")
    public User(String userId, String email, String password, Set<Role> roles, String userName, String userPhoneNumber, byte userSex, LocalDate userBirthDate, Boolean userReceiveEmail, Boolean userReceiveSms, Boolean userSmsCert, LocalDateTime userRegisterDateTime, List<UserAddress> userAddresses) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userSex = userSex;
        this.userBirthDate = userBirthDate;
        this.userReceiveEmail = userReceiveEmail;
        this.userReceiveSms = userReceiveSms;
        this.userSmsCert = userSmsCert;
        this.userRegisterDateTime = userRegisterDateTime;
        this.userAddresses = userAddresses;
    }

    public User() {

    }
}