package com.jjjteam.jmarket.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjjteam.jmarket.dto.AccountFormDTO;
import com.jjjteam.jmarket.security.token.PreAuthorizationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// AbstractAuthenticationProcessingFilter
// 웹 기반 인증 요청에 사용. 폼 POST, SSO 정보 또는 기타 사용자가 제공한 크리덴셜(크리덴셜은 사용자가 본인을 증명하는 수단)을 포함한 요청을 처리.
// Authentication 객체를 생성
public class FormLoginFilter extends AbstractAuthenticationProcessingFilter {
    // 3. 성공실패 인터페이스를 불러오기
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    // 1.
    protected FormLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public FormLoginFilter(
            AntPathRequestMatcher defaultUrl,
            AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler) {
        super(defaultUrl);
        this.authenticationSuccessHandler = successHandler;
        this.authenticationFailureHandler = failureHandler;
    }

    // 2. 가장 처음 인증 attemptAuthentication 메서드를 실행
    // 인증이 성공한다면 doFilter 메서드에서 successfulAuthentication 으로 메서드를 실행
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req,
            HttpServletResponse res
    )
            throws AuthenticationException, IOException, ServletException {

        // JSON 으로 변환
        AccountFormDTO dto = new ObjectMapper().readValue(
                req.getReader(),
                AccountFormDTO.class
        );

        // 사용자입력값이 존재하는지 비교
        PreAuthorizationToken token = new PreAuthorizationToken(dto);

        // PreAuthorizationToken 해당 객체에 맞는 Provider를
        // getAuthenticationManager 해당 메서드가 자동으로 찾아서 연결해 준다.
        // 자동으로 찾아준다고 해도 Provider 에 직접 PreAuthorizationToken 지정해 줘야 찾아갑니다.

        return super
                .getAuthenticationManager()
                .authenticate(token);
    }

    // 4. 인증 성공 or 실패 메서드 구현
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {
        this
                .authenticationSuccessHandler
                .onAuthenticationSuccess(req, res, authResult);
    }

    // 4.
    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            AuthenticationException failed
    ) throws IOException, ServletException {
        this
                .authenticationFailureHandler
                .onAuthenticationFailure(req, res, failed);
    }

}
