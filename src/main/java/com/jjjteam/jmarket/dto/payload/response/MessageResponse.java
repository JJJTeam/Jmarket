package com.jjjteam.jmarket.dto.payload.response;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        
        this.message = message;
    }

}
