package com.jjjteam.jmarket.dto.payload.response;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String userId;
    private String email;
    private List<String> roles;

    public UserInfoResponse(Long id, String userId, String email, List<String> roles) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }

}
