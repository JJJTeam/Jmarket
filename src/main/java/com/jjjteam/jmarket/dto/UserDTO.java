package com.jjjteam.jmarket.dto;


import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Slf4j
public class UserDTO {
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}$", message = "아이디를 4-20자로 입력해주세요.")
    private String userid;
    @Email
    private String email;
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=\\S+$).{8,}$", message = "잘못입력하셨습니다.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,}$", message = "잘못입력하셨습니다.")
    private String password;
    @Pattern(regexp = "^[ㄱ-ㅎ|가-힣|a-z|A-Z|]+$" , message = "잘못입력하셨습니다.(한글,영어만 가능)")
    private String userName;
    private String userPhoneNumber;
    private byte userSex;                   //성별  X
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate userBirthDate;             //회원생년월일  X
    private Boolean userReceiveEmail;          //이메일수신여부  X
    private Boolean userReceiveSms;            //문자수신여부  X
    private Boolean userSmsCert;               // 문자 인증 여부  X
    private LocalDateTime userRegisterDateTime; //회원가입시간  X
    private Set<Role> role;


    public UserDTO setRole(Set<Role> roles){
        this.role=roles;
        return this;
    }
    public UserDTO setPassword(String password){
        this.password = password;
        return this;
    }
    public User toEntity(){
        User user = User.builder()
                .userId(userid)
                .email(email)
                .password(password)
                .userName(userName)
                .userPhoneNumber(userPhoneNumber)
                .userSex(userSex)
                .userBirthDate(userBirthDate)
                .userReceiveEmail(userReceiveEmail)
                .userReceiveSms(userReceiveSms)
                .userSmsCert(userSmsCert)
                .userRegisterDateTime(userRegisterDateTime)
                .roles(role)
                .build();
        return user;
    }

}
