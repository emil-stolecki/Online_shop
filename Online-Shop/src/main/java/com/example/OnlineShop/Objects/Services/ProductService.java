package com.example.OnlineShop.Objects.Services;

import java.util.List;

import com.example.OnlineShop.Database.Dtos.ProductDto;
import com.example.OnlineShop.Database.Dtos.ProductLessDto;
import com.example.OnlineShop.Other.Filter;

public interface ProductService {

	
	ProductDto getById(Long id);
	List<ProductLessDto> getByCategoryandFilter(Filter filter);
	List<ProductLessDto> getByName(String name);
	List<ProductLessDto> getPopular();
	
	//methods for adding and editing products
}
