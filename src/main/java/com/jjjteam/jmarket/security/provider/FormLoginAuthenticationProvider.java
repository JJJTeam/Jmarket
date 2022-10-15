package com.jjjteam.jmarket.security.provider;


import com.jjjteam.jmarket.security.token.PostAuthorizationToken;
import com.jjjteam.jmarket.security.token.PreAuthorizationToken;
import com.jjjteam.jmarket.service.AccountServiceImpl;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//로그인한 사용자의 인증 권한을 검사합니다.
@Component
@RequiredArgsConstructor
//AuthenticationProvider 인터페이스는 화면에서 입력한 로그인 정보와 DB에서 가져온 사용자의 정보를 비교해주는 인터페이스
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    private final AccountServiceImpl accountService;

    // 4.
    private final PasswordEncoder passwordEncoder;

    // 1.  로그인한 사용자와 DB 사용자를 비교하는 메서드 authenticate() @Override 해줍니다.
    @Override
//    해당 인터페이스에 오버라이드되는 authenticate() 메서드는 화면에서 사용자가 입력한 로그인 정보를 담고 있는 Authentication 객체를 가지고 있다.
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        // 2. 해당 PreToken 에서 로그인한 유저의 정보를 변수에 담습니다.
        PreAuthorizationToken token = (PreAuthorizationToken) authentication;

        String username = token.getUsername();
        String password = token.getUserPassword();

        // 3. 로그인한 유저가 DB에 존재하는지 accountService 를 통해서 확인합니다.
        UserDetails accountDB = accountService.loadUserByUsername(username);

        // 4. 로그인한 유저와 DB에 존재하는 유저의 Password 가 동일한지 조회하는 메서드입니다. *무조건 비교 대상이 앞에 와야합니다.
        //로그인한 유저가 DB에 존재한다면 PostAuthorizationToken(권한이 부여된 토큰) 객체를 생성하여 return
        //PasswordEncoder 클래스를 주입 받으려면 PasswordEncoder Bean 을 등록해 줘야합니다.
        if (isCorrectPassword(password, accountDB.getPassword())) {
            return PostAuthorizationToken
                    .getTokenFormUserDetails(accountDB);
        }

        // 이곳까지 통과하지 못하면 잘못된 요청으로 접근하지 못한것 그러므로 throw 해줘야 한다.
        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    // 5.
    // Provider 를 연결 해주는 메소드 PreAuthorizationToken 사용한 filter 를 검색 후 연결
//    AuthenticationProvider 인터페이스가 지정된 Authentication 객체를 지원하는 경우에 true 를 리턴한다.
//    form action 진행 시 해당 클래스의 supports() > authenticate() 순으로 인증 절차 진행합니다.
//    사용자 인증이 완료되면 사용자에게 권한을 부여한 토큰을 생성하여 return 해줘야 합니다.
//    PostAuthorizationToken 클래스를 생성합니다.
//    PreAuthorizationToken.class.isAssignableFrom(authentication) 를 지정하므로서
//    Security 에서 Filter 에서 사용한 PreAuthorizationToken 를 참고해서 여러곳을 검색하여 해당 Provider 로 연결 해줍니다.
//    예시로 JWTProvider 같은경우에는 JwtPreProcessingToken 를 사용하고 있어서 JWTProvider 에는
//    JwtPreProcessingToken.class.isAssignableFrom(authentication) 로 연결되어 있어서 자동으로 Provider
//    검색하여 해당 Pre 클래스가 있는곳을 찾아 Provider 를 연결해 줍니다.
    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    // 4.
//    로그인한 유저와 DB에 존재하는 유저의 Password 가 동일한지 조회하는 메서드입니다. *무조건 비교 대상이 앞에 와야합니다.
//    로그인한 유저가 DB에 존재한다면 PostAuthorizationToken(권한이 부여된 토큰) 객체를 생성하여 return 합니다.
//    PasswordEncoder 클래스를 주입 받으려면 PasswordEncoder Bean 을 등록해 줘야합니다.
    private boolean isCorrectPassword(String password, String accountPassword) {
        return passwordEncoder.matches(password, accountPassword);
    }
}