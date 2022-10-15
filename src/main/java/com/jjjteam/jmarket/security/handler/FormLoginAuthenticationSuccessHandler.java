package com.jjjteam.jmarket.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjjteam.jmarket.dto.TokenDTO;
import com.jjjteam.jmarket.security.jwt.JwtFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjjteam.jmarket.security.token.PostAuthorizationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
//handler 인증 성공(인증객체 생성)
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // 2.
    private final JwtFactory factory;
    private final ObjectMapper objectMapper;

    // 1.
//    onAuthenticationSuccess 메서드를 @Override 해줍니다.
//    Token 값을 정형화된 DTO를 만들어서 res 으로 내려주는 역할을 수행합니다.
//    인증결과 객체 auth 를 PostAuthorizationToken 객체 변수에 담아줍니다.
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest req,
            HttpServletResponse res,
            Authentication auth
    ) throws IOException {

        PostAuthorizationToken token   = (PostAuthorizationToken) auth;
        UserDetails            userDetails = token.getUserDetails();

        // 2.
//        ostAuthorizationToken 객체에 담아줄 JWT Token 을 생성해야 합니다.
//        그 후 processRespone 메서드를 통해서 Response 상태와 jwt 값을 전송합니다.
        String tokenString = factory.generateToken(userDetails);

        // 3.
//        Token 값을 정형화된 DTO 를 만들어 줍니다.
        TokenDTO tokenDTO = new TokenDTO(tokenString, userDetails.getUsername());

        processResponse(res, tokenDTO);
    }

    private void processResponse(
            HttpServletResponse res,
            TokenDTO dto
    ) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(objectMapper.writeValueAsString(dto));
    }
}