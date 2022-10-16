package com.jjjteam.jmarket;



import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

@RunWith(SpringRunner.class)
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
}