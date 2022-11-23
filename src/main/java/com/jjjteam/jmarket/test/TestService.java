package com.jjjteam.jmarket.test;



import com.jjjteam.jmarket.constant.ERole;
import com.jjjteam.jmarket.model.Role;
import com.jjjteam.jmarket.model.User;
import com.jjjteam.jmarket.model.UserAddress;
import com.jjjteam.jmarket.repository.RoleRepository;
import com.jjjteam.jmarket.repository.UserAddressRepository;
import com.jjjteam.jmarket.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//서버 시작, 종료할때 실행하는 메서드
//@Slf4j
//@Service
//public class TestService implements CommandLineRunner, ApplicationListener<ContextClosedEvent>, InitializingBean, DisposableBean {

//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    UserAddressRepository userAddressRepository;
//
//    @PostConstruct
//    private void init(){
////                System.out.println("빈이 완전히 생성된 후에 한번 수행될 메서드에 붙입니다.");
//        roleRepository.save(new Role(ERole.ROLE_USER));
//        roleRepository.save(new Role(ERole.ROLE_MODERATOR));
//        roleRepository.save(new Role(ERole.ROLE_ADMIN));
//        // 권한내용을 DB에 넣어준다.
////        User user = new User("test","test@test",encoder.encode("test"));
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        log.info("roles : {}", roles);
//        User user = User.builder()
//                .userId("test")
//                .email("test@test.com")
//                .password("test")
//                .roles(roles)
//                .userBirthDate(LocalDate.now())
//                .userName("유저이름")
//                .userSex((byte) 1)
//                .userPhoneNumber("010-3034-7578")
//                .userRegisterDateTime(LocalDateTime.now())
//                .build();
//        userRepository.save(user);
//        userAddressRepository.save(
//                UserAddress.builder()
//                        .address("서울 한강중심")
//                        .addressDetail("403호")
//                        .addressPhoneNumber("010-4343-7575")
//                        .user(userRepository.getReferenceById(1L))
//                        .person("우리집 고양이")
//                        .postCode("1234")
//                        .defaultAddress(true)
//                        .build());
//        userAddressRepository.save(
//                UserAddress.builder()
//                        .address("부산광역시 행복동 행복아파트")
//                        .addressDetail("123403호")
//                        .addressPhoneNumber("010-4343-1324")
//                        .user(userRepository.getReferenceById(1L))
//                        .person("개똥이")
//                        .postCode("1234")
//                        .defaultAddress(null)
//                        .build());
//        userAddressRepository.save(
//                UserAddress.builder()
//                        .address("주소테스트3")
//                        .addressDetail("1203호")
//                        .addressPhoneNumber("010-4123-1324")
//                        .user(userRepository.getReferenceById(1L))
//                        .person("하나무라")
//                        .defaultAddress(null)
//                        .postCode("1234")
//                        .build());
//        userAddressRepository.save(
//                UserAddress.builder()
//                        .address("주소테스트4")
//                        .addressDetail("12호")
//                        .addressPhoneNumber("010-413-1324")
//                        .user(userRepository.getReferenceById(1L))
//                        .person("하라")
//                        .postCode("1234")
//                        .defaultAddress(null)
//                        .build());
//
//    }
//    @Override
//    public void run(String... args) throws Exception{
////        System.err.println("인터페이스 구현 메서드, 에플리케이션이 한번 실행될때 한번 실행됩");
//    }
//    @Override
//    public  void onApplicationEvent(ContextClosedEvent event){
////        System.err.println("ApplicationListener<ContextClosedEvent> 인터페이스 구현 메서드 입니다. '애플리케이션'이 죽었을 때 '한 번' 실행됩니다.");
////        System.err.println("이벤트 발생 시간(timestamp) : " + event.getTimestamp());
//    }
//    @Override
//    public void afterPropertiesSet() throws Exception{
////        System.err.println("InitializingBean 인터페이스 구현 메서드입니다. TestService 'Bean'이 생성될 때 마다 호출되는 메서드 입니다.");
//    }
//    @Override
//    public void destroy() throws Exception{
////        System.err.println("DisposableBean 인터페이스 구현 메서드입니다. TestService 'Bean'이 소멸될 때 마다 호출되는 메서드입니다");
//    }
//}
