package com.jjjteam.jmarket.security.token;

import com.jjjteam.jmarket.dto.AccountFormDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String username, String password) {
        super(username, password);
    }

    public PreAuthorizationToken(AccountFormDTO dto) {
        this(dto.getUsername(), dto.getPassword());
    }

    public String getUsername() {
        return (String) super.getPrincipal();
    }

    public String getUserPassword() {
        return (String) super.getCredentials();
    }
}