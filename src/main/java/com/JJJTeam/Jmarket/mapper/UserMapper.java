package com.jjjteam.jmarket.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;

import com.jjjteam.jmarket.dto.UserDTO;
import com.jjjteam.jmarket.sql.UserSQL;



@Mapper
public interface UserMapper {
	 @InsertProvider(type = UserSQL.class, method = "UserSingUp")
     public void UserSingUp(UserDTO userDTO);


}
