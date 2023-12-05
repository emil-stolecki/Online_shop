package com.example.OnlineShop.Database.Dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.example.OnlineShop.Database.Models.ItemInCartModel;
import com.example.OnlineShop.Database.Models.ReviewModel;
import com.example.OnlineShop.Database.Models.RoleModel;


public record UserDto(Long id,
					 String login,
					 String email,
					 String firstName,
					 String lastName,
					 String encryptedPassword,
					 LocalDateTime joined,
					 Long roleId) {


}


