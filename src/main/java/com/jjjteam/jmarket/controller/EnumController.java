package com.jjjteam.jmarket.controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjjteam.jmarket.constant.EnumModel;
import com.jjjteam.jmarket.constant.EnumValue;
import com.jjjteam.jmarket.constant.UserRole;

@RestController
public class EnumController {

    @GetMapping("/enum")
    public Map<String, Object> getEnum() {
        Map<String, Object> enums    = new LinkedHashMap<>();
        Class<UserRole>userRole = UserRole.class;
        enums.put("userRole", userRole.getEnumConstants());

        return enums;
    }
    @GetMapping("/value")
    public Map<String, List<EnumValue>> getEnumValue() {
        Map<String, List<EnumValue>> enumValues = new LinkedHashMap<>();

        enumValues.put("userRole", toEnumValues(UserRole.class));

        return enumValues;
    }

    private List<EnumValue> toEnumValues(Class<? extends EnumModel> e) {
        /*
         * Java8이 아닐경우
         * List<EnumValue> enumValues = new ArrayList<>();
         * for (EnumModel enumType : e.getEnumConstants()) {
         *     enumValues.add(new EnumValue(enumType));
         * }
         * return enumValues;
         */
        return Arrays
                .stream(e.getEnumConstants())
                .map(EnumValue::new)
                .collect(Collectors.toList());
    }
}