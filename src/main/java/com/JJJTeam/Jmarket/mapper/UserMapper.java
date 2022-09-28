package com.JJJTeam.Jmarket.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;



import com.JJJTeam.Jmarket.DTO.UserDTO;
import com.JJJTeam.Jmarket.SQL.UserSQL;

@Mapper
public interface UserMapper {
	 @InsertProvider(type = UserSQL.class, method = "UserSingUp")
     public void UserSingUp(UserDTO userDTO);


}
