package com.example.OnlineShop.Database.Dtos;

import java.util.List;

import com.example.OnlineShop.Database.Models.PreviewImageModel;
import com.example.OnlineShop.Database.Models.ReviewModel;

public record ProductDto(Long id,
						String name,
						String description,
						int amountInStock,
						float price,
						String seller,
						List<Integer> categories,
						List<PreviewImageModel> images,
						List<ReviewModel> reviews) {

	public static class Builder{
		private Long id;	
		private String name;
		private String description;	
		private int amountInStock;
		private float price;
		private String seller;
		private List<Integer> categories;
		private List<PreviewImageModel> images;
		private List<ReviewModel> reviews;
		
		
		
		public Builder id(Long id) {
            this.id = id;
            return this;
        }
		
		public Builder name(String name) {
            this.name = name;
            return this;
        }
		public Builder description(String description) {
            this.description = description;
            return this;
        }
		public Builder amountInStock(int amountInStock) {
            this.amountInStock = amountInStock;
            return this;
        }
		public Builder price(float price) {
            this.price = price;
            return this;
        }
		public Builder seller(String seller) {
            this.seller = seller;
            return this;
        }
		
		public Builder categories(List<Integer> categories) {
            this.categories = categories;
            return this;
        }
		public Builder images(List<PreviewImageModel> images) {
            this.images = images;
            return this;
        }
		public Builder reviews(List<ReviewModel> reviews) {
            this.reviews = reviews;
            return this;
        }
		
		public ProductDto build() {
			return new ProductDto(id,name,description,amountInStock,price,seller,categories,images,reviews);
		}
		
	}
}
