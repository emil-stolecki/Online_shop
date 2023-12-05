package com.example.OnlineShop.Database.Dtos;

import java.util.List;

import com.example.OnlineShop.Database.Models.CategoryModel;
import com.example.OnlineShop.Database.Models.PreviewImageModel;
import com.example.OnlineShop.Database.Models.ReviewModel;

public record ProductDto(Long id,
						String name,
						String description,
						int amountInStock,
						double price,
						String seller,
						List<CategoryModel> categories,
						List<PreviewImageModel> images,
						List<ReviewModel> reviews) {

	
}
