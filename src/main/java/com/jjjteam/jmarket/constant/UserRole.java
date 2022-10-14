package com.jjjteam.jmarket.constant;

public enum UserRole implements EnumModel {
    ADMIN("ROLE_ADMIN"),  // admin : Key 값 , ROLE_ADMIN : value 값
    USER("ROLE_USER");
    
    private String roleName;
    
    UserRole(String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String getKey() {
        return name();
    }
    
    @Override
    public String getValue() {
        return roleName;
    }
}