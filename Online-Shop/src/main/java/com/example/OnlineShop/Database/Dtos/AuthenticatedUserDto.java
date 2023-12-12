package com.example.OnlineShop.Database.Dtos;



public record AuthenticatedUserDto(Long id,
						   String login,
						   String email) {
	
	//this will be sent to the frontend if user is authenticated successfully
	
}
