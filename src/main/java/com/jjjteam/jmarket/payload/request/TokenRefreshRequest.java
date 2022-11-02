package com.jjjteam.jmarket.payload.request;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

@Slf4j
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;


    public String getRefreshToken() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.refreshToken = refreshToken;
    }
}
