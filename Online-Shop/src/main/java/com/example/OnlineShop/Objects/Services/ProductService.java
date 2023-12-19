package com.example.OnlineShop.Objects.Services;

import java.util.List;

import com.example.OnlineShop.Database.Dtos.CategoryDto;
import com.example.OnlineShop.Database.Dtos.ProductDto;
import com.example.OnlineShop.Database.Dtos.ProductLessDto;
import com.example.OnlineShop.Other.Filter;

public interface ProductService {

	
	ProductDto getById(Long id);
	List<ProductLessDto> getByCategoryandFilter(Filter filter);
	List<ProductLessDto> getPopular();
	
	List<CategoryDto> getCategories();
	Long getByCategoryandFilterCount(Filter filter);
}