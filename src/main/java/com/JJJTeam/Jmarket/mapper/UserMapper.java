package com.JJJTeam.Jmarket.mapper;

import com.JJJTeam.Jmarket.DTO.UserDTO;

public interface UserMapper {
<<<<<<< HEAD
	int signUp(UserDTO userDTO);
=======
	 @InsertProvider(type = UserSQL.class, method = "UserSingUp")

	 public void UserSingUp(UserDTO userDTO);

	 
	 

>>>>>>> 71ab30c... 수진브런치로이동
}
