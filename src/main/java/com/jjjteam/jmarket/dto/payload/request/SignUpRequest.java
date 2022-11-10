package com.jjjteam.jmarket.dto.payload.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignUpRequest {
//    @NotBlank
//    @Size(max = 50)
//    @Email
//    @Size(min = 6, max = 40)
    private String userid;
    private String email;
    private Set<String> role;
    private String password;
//    private String userName;
//    private String userPhoneNumber;
//    private byte userSex;                   //성별
//    private Date userBirthDate;             //회원생년월일
//    private byte userReceiveEmail;          //이메일수신여부
//    private byte userReceiveSms;            //문자수신여부
//    private byte userSmsCert;               // 문자 인증 여부
//    private LocalDate userRegisterDateTime; //회원가입시간

}
