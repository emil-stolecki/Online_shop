package com.example.OnlineShop.Database.Dtos;

import com.example.OnlineShop.Database.Models.ProductModel;
import com.example.OnlineShop.Database.Models.UserModel;

public record ItemInCartDto(Long id,							
							Long productId,
							String productName,
							int amount) {
	
	
}
