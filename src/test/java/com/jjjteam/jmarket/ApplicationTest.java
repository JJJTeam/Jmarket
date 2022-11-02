package com.jjjteam.jmarket;




import com.jjjteam.jmarket.model.ERole;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private UserAddressRepository userAddressRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;

//	@After
//	public void cleanup() {
//		/*
//		 * 이후 테스트 코드에 영향을 끼치지 않기 위해
//		 * 테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
//		 */
//		accountRepository.deleteAll();
//	}
//
	@Test
	public void userInsert() {


	}
	@Test
	public void userAddressInsert(){


		userAddressRepository.save(
				UserAddress.builder()
						.address("test8")
						.user(userRepository.getReferenceById(1L))
						.build()
		);


	}





}