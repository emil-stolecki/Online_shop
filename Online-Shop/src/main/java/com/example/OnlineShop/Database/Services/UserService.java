package com.example.OnlineShop.Database.Services;

import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;

public interface UserService {

	
	boolean register(UserRegistrationDto registration);
	
	UserDto updateProfile(Long id);
	boolean deleteprofile(Long id);
	
}
