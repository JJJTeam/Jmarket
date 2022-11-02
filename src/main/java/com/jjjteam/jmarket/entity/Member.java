package com.jjjteam.jmarket.entity;

import com.jjjteam.jmarket.constant.Role;
import com.jjjteam.jmarket.dto.MemberFormDTO;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Setter
@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member")
public class Member extends BaseEntity{

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private String address;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder){
        
        Member member = Member.builder()
                .name(memberFormDTO.getName())
                .email(memberFormDTO.getEmail())
                .address(memberFormDTO.getAddress())
                .password( passwordEncoder.encode( memberFormDTO.getPassword() ) ) // BCryptPasswordEncoder Bean 을 파라미터로 넘겨서 비번을 암호화함
//                .role(Role.USER)
                .role(Role.ADMIN)
                .build();
        
        return member;
    }
    // 여기서는 반대로 ! 이렇게 해줌
}
