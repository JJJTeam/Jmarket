package com.jjjteam.jmarket;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.jjjteam.jmarket.model.Board;
import com.jjjteam.jmarket.repository.BoardRepository;

import java.time.LocalDateTime;
// @RunWith(SpringRunner.class)


@SpringBootTest
public class ApplicationTest {

//	@Autowired
//	private AccountRepository accountRepository;
//
//	@After
//	public void cleanup() {
//		/*
//		 * 이후 테스트 코드에 영향을 끼치지 않기 위해
//		 * 테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
//		 */
//		accountRepository.deleteAll();
//	}
//
//	@Test
//	public void userInsert() {
//		//given
//		accountRepository.save(
//				Account.builder()
//						.username("testUsername")
//						.password("1234")
//						.role(UserRole.USER)
//						.build()
//		);
//
//		// when
//		List<Account> userList = accountRepository.findAll();
//
//		// then
//		Account account = userList.get(0);
//		assertThat(account.getUsername(), is("testUsername"));
//		assertThat(account.getPassword(), is("1234"));
//		assertThat(account.getRole(), is(UserRole.USER));
//	}
	
	
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