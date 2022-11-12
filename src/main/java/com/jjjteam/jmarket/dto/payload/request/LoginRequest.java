package com.jjjteam.jmarket.dto.payload.request;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

@Slf4j
public class LoginRequest {
    @NotBlank
    private String userid;

    @NotBlank
    private String password;

    public String getUserid() {
        
        return userid;
    }

    public void setUserid(String userid) {
        
        this.userid = userid;
    }

    public String getPassword() {
        
        return password;
    }

    public void setPassword(String password) {
        
        this.password = password;
    }
}
