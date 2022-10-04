package com.JJJTeam.Jmarket.Service;


import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JJJTeam.Jmarket.DTO.UserDTO;
import com.JJJTeam.Jmarket.Mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService  {
	
	//객체 주입
	@Autowired
    UserMapper userMapper;
	
	// 트랜잭션 보장이 된 메소드로 설정
	@Transactional
	public void joinUser(UserDTO userDTO) {
		userMapper.addUser(userDTO);
	}
	
	
	
	public UserDTO getSelectUser(UserDTO userDTO) throws Exception{
		return userMapper.selectUser(userDTO);
	}

	
	
}
