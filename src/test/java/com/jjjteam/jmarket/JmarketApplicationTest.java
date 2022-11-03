package com.jjjteam.jmarket;

import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.BoardRepository;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import com.jjjteam.jmarket.service.BoardService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;


// @RunWith(SpringRunner.class)


@SpringBootTest
class JmarketApplicationTest {

//	@Autowired
//	private UserAddressRepository userAddressRepository;
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	PasswordEncoder encoder;
//	@Autowired
//	private RoleRepository roleRepository;

	

	@Autowired
	private BoardRepository boardRepository;
	private BoardService boardService;

	@Test
	void testJpa() {
		
		for(int i =1; i<=300; i++) {
			String subject =String.format("테스트데이터 : ${{%03d}", i);
			String content= "내용무 ";

			this.boardService.registerForm(subject, content);
		}
	}
	
	
}
