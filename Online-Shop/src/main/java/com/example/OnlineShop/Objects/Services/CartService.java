package com.example.OnlineShop.Objects.Services;

import java.util.List;

import com.example.OnlineShop.Database.Dtos.ItemInCartDto;
import com.example.OnlineShop.Database.Dtos.ProductDto;

public interface CartService {

	
	boolean addToCart(Long userId,Long productId,int amount);
	boolean removeFromCart(Long id);
	boolean updateAmount(Long id,int amount);
	List<ItemInCartDto> getProducts(Long userId);
	
}
