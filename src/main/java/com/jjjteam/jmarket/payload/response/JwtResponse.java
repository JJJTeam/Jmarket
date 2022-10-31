package com.jjjteam.jmarket.payload.response;


import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, List<String> roles) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return token;
    }

    public void setAccessToken(String accessToken) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.token = accessToken;
    }

    public String getTokenType() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return type;
    }

    public void setTokenType(String tokenType) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.type = tokenType;
    }

    public Long getId() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return id;
    }

    public void setId(Long id) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.id = id;
    }

    public String getEmail() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return email;
    }

    public void setEmail(String email) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.email = email;
    }

    public String getUsername() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return username;
    }

    public void setUsername(String username) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.username = username;
    }

    public List<String> getRoles() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return roles;
    }

    public String getRefreshToken() {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.refreshToken = refreshToken;
    }
}
