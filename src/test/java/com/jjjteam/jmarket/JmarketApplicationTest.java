package com.jjjteam.jmarket;

import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.BoardRepository;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;

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

	@Test
	void testJpa() {
		Board b1 = new Board();
		b1.setSubject("board에 잘들어가는지 체크1111111");
		b1.setContent("안에 들어가는 content 체크까지 1111");
		b1.setCreateDate(LocalDateTime.now());
		this.boardRepository.save(b1);
		
		Board b2 = new Board();
		b2.setSubject("board에 잘들어가는지 체크2222222");
		b2.setContent("안에 들어가는 content 체크까지22222");
		b2.setCreateDate(LocalDateTime.now());
		this.boardRepository.save(b2);
	}
}
