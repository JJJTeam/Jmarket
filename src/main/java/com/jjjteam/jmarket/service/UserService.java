package com.jjjteam.jmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.mapper.UserMapper;



@Service
public class UserService {
	 @Autowired UserMapper mapper;
	   public void signUp(UserDTO userDTO) {
	      mapper.UserSingUp(userDTO);
	   }
}
