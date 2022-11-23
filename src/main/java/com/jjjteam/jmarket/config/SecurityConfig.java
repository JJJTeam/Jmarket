package com.jjjteam.jmarket.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
         securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
@Slf4j
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf 사용안함
        http.csrf().disable();  //로그인 로그아웃 회원가입 CSRF 적용함

        //URL 인증여부 설정.
        http.authorizeRequests()
                .antMatchers( "/signup", "/", "/login", "/css/**","/js/**","/images/**","/fonts/**", "/vendor/**").permitAll()
        .anyRequest().permitAll();

		// 로그인 관련 설정.
		http.formLogin().loginPage("/login").loginProcessingUrl("/login") // Post 요청
				.defaultSuccessUrl("/").failureUrl("/user/login?error").permitAll();

		// 로그아웃 설정
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/");

		// 비인가자 요청시 보낼 Api URI
		http.exceptionHandling().accessDeniedPage("/forbidden");

		return http.build();
	}

}
