package com.jjjteam.jmarket.dto.payload.request;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

@Slf4j
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;


    public String getRefreshToken() {
        
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        
        this.refreshToken = refreshToken;
    }
}
