package com.jjjteam.jmarket.controller;


import com.jjjteam.jmarket.constant.EnumMapper;
import com.jjjteam.jmarket.constant.EnumValue;
import com.jjjteam.jmarket.constant.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor // EnumMapper 생성자 생략 가능
public class EnumController {
    private final EnumMapper enumMapper;

    @GetMapping("/enum")
    public Map<String, Object> getEnum() {
        Map<String, Object> enums = new LinkedHashMap<>();
        Class<UserRole> userRole = UserRole.class;
        enums.put("userRole", userRole.getEnumConstants());

        return enums;
    }

    @GetMapping("/mapper")
    public Map<String, List<EnumValue>> getMapper() {

        return enumMapper.getAll();
    }

}