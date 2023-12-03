package com.example.OnlineShop.Database.Dtos;



public record UserLoginDto(String login,
						   String email,
						   String encryptedPassword) {
	//user can provide either login or e-mail for authentication
	
}
