package com.jjjteam.jmarket.advice;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        
        return statusCode;
    }

    public Date getTimestamp() {
        
        return timestamp;
    }

    public String getMessage() {
        
        return message;
    }

    public String getDescription()
    {
        
        return description;
    }
}