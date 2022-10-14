package com.jjjteam.jmarket.test;


import com.jjjteam.jmarket.constant.UserRole;
import com.jjjteam.jmarket.model.Account;
import com.jjjteam.jmarket.repository.AccountRepository;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
//서버 시작, 종료할때 실행하는 메서드
@Service
public class TestService implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, InitializingBean, DisposableBean {

    @Autowired
//    private AccountRepository accountRepository;
    @PostConstruct
    private void init(){
//                System.out.println("빈이 완전히 생성된 후에 한번 수행될 ㅂ메서드에 붙입니다.");
//        accountRepository.save(
//                Account.builder()
//                        .username("test")
//                        .password("1234")
//                        .role(UserRole.USER)
//                        .build()
//        );
        // 테스트 유저 추가 메서드

    }
    @Override
    public void run(String... args) throws Exception{
//        System.err.println("인터페이스 구현 메서드, 에플리케이션이 한번 실행될때 한번 실행됩");
    }
    @Override
    public  void onApplicationEvent(ContextClosedEvent event){
//        System.err.println("ApplicationListener<ContextClosedEvent> 인터페이스 구현 메서드 입니다. '애플리케이션'이 죽었을 때 '한 번' 실행됩니다.");
//        System.err.println("이벤트 발생 시간(timestamp) : " + event.getTimestamp());
    }
    @Override
    public void afterPropertiesSet() throws Exception{
//        System.err.println("InitializingBean 인터페이스 구현 메서드입니다. TestService 'Bean'이 생성될 때 마다 호출되는 메서드 입니다.");
    }
    @Override
    public void destroy() throws Exception{
//        System.err.println("DisposableBean 인터페이스 구현 메서드입니다. TestService 'Bean'이 소멸될 때 마다 호출되는 메서드입니다");
    }
}
