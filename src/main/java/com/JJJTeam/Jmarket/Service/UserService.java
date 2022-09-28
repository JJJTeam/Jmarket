package com.JJJTeam.Jmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JJJTeam.Jmarket.DTO.UserDTO;
import com.JJJTeam.Jmarket.mapper.UserMapper;

@Service
public class UserService {
	 @Autowired UserMapper mapper;
	   public int signUp(UserDTO userDTO) {
	      return mapper.signUp(userDTO);
	   }
}
