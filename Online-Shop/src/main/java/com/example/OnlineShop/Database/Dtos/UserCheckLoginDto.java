package com.example.OnlineShop.Database.Dtos;

import java.util.List;

public record UserCheckLoginDto(Long id,
								String login,								
								String password,
								String role
								) {

}
