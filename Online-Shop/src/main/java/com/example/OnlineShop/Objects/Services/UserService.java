package com.example.OnlineShop.Objects.Services;

import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;

public interface UserService {

	
	boolean register(UserRegistrationDto registration);
	
	boolean updateProfile(UserDto user);
	boolean deleteprofile(Long id);
	
	UserDto getUserDetails(long id);
	
}
