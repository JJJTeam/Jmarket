package com.jjjteam.jmarket.dto.payload.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class SignUpRequest {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "아이디를 4-20자로 입력해주세요(특수문자불가능)")
    private String userid;
//    @Email
    private String email;
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,20}$")
    private String password;
//    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|]+$")
    private String userName;

    private String userPhoneNumber;
    private byte userSex;                   //성별  X
    private Date userBirthDate;             //회원생년월일  X
    private Boolean userReceiveEmail;          //이메일수신여부  X
    private Boolean userReceiveSms;            //문자수신여부  X
    private Boolean userSmsCert;               // 문자 인증 여부  X
    private LocalDate userRegisterDateTime; //회원가입시간  X

    private Set<String> role;
}
