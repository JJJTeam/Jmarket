package com.JJJTeam.Jmarket.Mapper;

import org.apache.ibatis.annotations.Mapper;
import com.JJJTeam.Jmarket.DTO.UserDTO;

@Mapper
public interface UserMapper {
	void addUser(UserDTO userDTO);
	
	public UserDTO selectUser(UserDTO userDTO)throws Exception;
}
