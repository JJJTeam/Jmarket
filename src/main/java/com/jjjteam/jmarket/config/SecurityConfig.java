package com.jjjteam.jmarket.config;

import com.jjjteam.jmarket.security.filter.FormLoginFilter;
import com.jjjteam.jmarket.security.handler.FormLoginAuthenticationFailureHandler;
import com.jjjteam.jmarket.security.handler.FormLoginAuthenticationSuccessHandler;
import com.jjjteam.jmarket.security.provider.FormLoginAuthenticationProvider;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final FormLoginAuthenticationSuccessHandler formLoginAuthenticationSuccessHandler;
	private final FormLoginAuthenticationFailureHandler formLoginAuthenticationFailureHandler;

	private final FormLoginAuthenticationProvider provider;

	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}

	// 1.
	protected FormLoginFilter formLoginFilter() throws Exception {
		FormLoginFilter filter = new FormLoginFilter(
				new AntPathRequestMatcher("/api/account/login", HttpMethod.POST.name()),
				formLoginAuthenticationSuccessHandler,
				formLoginAuthenticationFailureHandler
		);
		filter.setAuthenticationManager(super.authenticationManagerBean());

		return filter;
	}


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

//	@Bean
//	public SecurityFilterChain securityFilterChain(AuthenticationManagerBuilder  auth) throws Exception {
//		auth
//				.authenticationProvider(this.provider);
//		return auth.build();
//	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf()
			.disable();

		http
			.headers()
			.frameOptions()
			.disable();
		return http.build();
	}

}
