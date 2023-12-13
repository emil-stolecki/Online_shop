package com.example.OnlineShop.Objects.Services;

import com.example.OnlineShop.Database.Dtos.AuthenticatedUserDto;
import com.example.OnlineShop.Database.Dtos.UserCheckLoginDto;
import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;

public interface UserService {

	
	boolean register(UserRegistrationDto registration);
	
	boolean updateProfile(UserDto user);
	boolean deleteprofile(Long id);
	
	UserDto getUserDetails(long id);
	AuthenticatedUserDto getAuthenticatedUser (String login);
	UserCheckLoginDto getCredentialsbyEmail (String email);
	UserCheckLoginDto getCredentialsbyLogin (String login);
	
}
