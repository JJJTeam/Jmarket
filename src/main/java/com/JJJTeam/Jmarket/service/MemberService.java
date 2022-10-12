package com.JJJTeam.Jmarket.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.JJJTeam.Jmarket.dto.MemberDTO;


@Service
public interface MemberService {
	 @Bean
	public void join(MemberDTO dto) throws Exception;
}
