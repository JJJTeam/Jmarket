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

//    @Autowired
//    UserDetailsServiceImpl userDetailsService;

//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;

//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }

//	@Override
//	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        
//        return authConfig.getAuthenticationManager();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable()
//			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
//			.antMatchers("/api/test/**").permitAll()
//			.anyRequest().authenticated();
//
//		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf 사용안함
        http.csrf().disable();  //로그인 로그아웃 회원가입 CSRF 적용함

        //URL 인증여부 설정.
        http.authorizeRequests()
                .antMatchers( "/signup", "/", "/login", "/css/**","/js/**","/images/**","/fonts/**", "/vendor/**").permitAll()
                //@Secured("ROLE_ADMIN")으로 대체
//                .antMatchers("/api/admin").hasRole("ADMIN")
//                .anyRequest().authenticated();
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
