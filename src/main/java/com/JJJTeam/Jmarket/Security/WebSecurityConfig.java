package com.JJJTeam.Jmarket.Security;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.JJJTeam.Jmarket.Service.MemberService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final MemberService memberService;
	
	@Override
	//인증을 무시할 경로 설정
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**","/js/**","/img/**","h2-console/**"); //h2-console 화면 사용하기 위함
	}
	
	@Override
	//http 관련 인증 설정 가능
	public void configure(HttpSecurity http) throws Exception{
		http
				.csrf().disable().headers().frameOptions().disable()
				.and()
					.authorizeRequests()
						.antMatchers("/login/login","/login/login_form","/login/user").permitAll() // 누구나 접근 가능
						.antMatchers("/login/").hasRole("USER") //USER, ADMIN 만 접근 가능
						.antMatchers("/login/admin").hasRole("ADMIN") //ADMON 만 접근 가능
						.anyRequest().authenticated() //나머지는 권한이 있기만 하면 접근 가능
				.and()
					.formLogin() //로그인에 대한 설정
						.loginPage("/login/login")	//로그인 페이지 설정
						.defaultSuccessUrl("/login/") //로그인 성공 시 연결되는 주소 
				.and()
					.logout()
						.logoutSuccessUrl("/login/login")	 //로그아웃 성공시 연결되는 주소
						.invalidateHttpSession(true) //로그아웃 시 저장해 둔 세션 날리기
		;
	}
	
	@Override
	//로그인 시 필요한 정보를 가져옴
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(memberService) //유저 정보는 userService에서 가져온다
			.passwordEncoder(new BCryptPasswordEncoder()); // 패스워드 인코더는 passwordEncoder(BCrypt 사용)
	}
	
	
}
