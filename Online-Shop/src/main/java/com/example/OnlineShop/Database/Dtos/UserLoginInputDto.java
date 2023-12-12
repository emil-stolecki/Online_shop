package com.example.OnlineShop.Database.Dtos;

public record UserLoginInputDto(String login /*can be either login or e-mail*/,								
								String password) {
	//this is Users input not a record from the database
}
