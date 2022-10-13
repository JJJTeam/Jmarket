package com.jjjteam.jmarket.config;

import com.jjjteam.jmarket.constant.EnumMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order(1)
@Configuration
public class WebConfig {
    //EnumMapper 을 Bean으로 등록해서 가장 처음으로 실행
    @Bean
    public EnumMapper enumMapper() {
        return new EnumMapper();
    }
}