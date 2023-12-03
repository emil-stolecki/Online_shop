package com.example.OnlineShop.Database.Services;

import java.util.List;

import com.example.OnlineShop.Database.Dtos.ProductDto;

public interface ProductService {

	
	ProductDto getById(Long id);
	List<ProductDto> getByCategory(String category);
	List<ProductDto> getByName(String name);
	
	
	//methods for adding and editing products
}
