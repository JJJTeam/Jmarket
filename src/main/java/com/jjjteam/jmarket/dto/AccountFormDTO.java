package com.jjjteam.jmarket.dto;

import com.jjjteam.jmarket.constant.UserRole;
import com.jjjteam.jmarket.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class AccountFormDTO {

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "영어 혹은 숫자만 입력 가능합니다.")
    @Length(min = 1, max = 10)
    @NotBlank(message = "아이디는 필수로 작성해야 합니다.")
    private String username;

    private String password;

    private final UserRole role = UserRole.USER;

    public Account toEntity() {
        return Account.builder()
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .build();
    }
}