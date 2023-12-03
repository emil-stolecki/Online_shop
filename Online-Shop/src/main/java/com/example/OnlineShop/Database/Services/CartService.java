package com.example.OnlineShop.Database.Services;

import java.util.List;

import com.example.OnlineShop.Database.Dtos.ProductDto;

public interface CartService {

	
	boolean addToCart(Long userId,Long productId);
	boolean removeFromCart(Long id);
	boolean updateAmount(Long id,int amount);
	List<ProductDto> getProducts(Long userId);
	boolean removeAll(Long userId);
	
}
