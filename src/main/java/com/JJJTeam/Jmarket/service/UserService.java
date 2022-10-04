package com.JJJTeam.Jmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JJJTeam.Jmarket.dto.UserDTO;
import com.JJJTeam.Jmarket.mapper.UserMapper;



@Service
public class UserService {
	 @Autowired UserMapper mapper;
	   public void signUp(UserDTO userDTO) {
	      mapper.UserSingUp(userDTO);
	   }
}
