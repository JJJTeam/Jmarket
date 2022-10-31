package com.jjjteam.jmarket.payload.response;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        log.info("현재클래스{}, 현재 메소드{}",Thread.currentThread().getStackTrace()[1].getClassName(),Thread.currentThread().getStackTrace()[1].getMethodName());
        this.message = message;
    }

}
