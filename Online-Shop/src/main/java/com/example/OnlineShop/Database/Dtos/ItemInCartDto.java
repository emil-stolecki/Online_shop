package com.example.OnlineShop.Database.Dtos;

import com.example.OnlineShop.Database.Models.ProductModel;
import com.example.OnlineShop.Database.Models.UserModel;

public record ItemInCartDto(Long id,
							UserModel user,
							ProductModel product,
							int amount) {
	
	public static class Builder{
		private Long id;
		private UserModel user;
		private ProductModel product;
		private int amount;
		
		public Builder id(Long id) {
			this.id=id;
			return this;
		}
		public Builder user(UserModel user) {
			this.user=user;
			return this;
		}
		public Builder product(ProductModel product) {
			this.product=product;
			return this;
		}
		public Builder id(int amount) {
			this.amount=amount;
			return this;
		}
		
		public ItemInCartDto builder() {
			return new ItemInCartDto(id,user,product,amount);
		}
	}
}
