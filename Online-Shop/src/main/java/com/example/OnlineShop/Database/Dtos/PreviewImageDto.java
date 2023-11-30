package com.example.OnlineShop.Database.Dtos;

import com.example.OnlineShop.Database.Models.ProductModel;

public record PreviewImageDto(Long id, ProductModel product,String image) {

	public static class Builder{
		private Long id;
		private ProductModel product;
		private String image;
		
		
		public Builder id (Long id) {
			this.id=id;
			return this;
		}
		public Builder product (ProductModel product) {
			this.product=product;
			return this;
		}
		public Builder image (String image) {
			this.image=image;
			return this;
		}
		
		public PreviewImageDto builde() {
			return new PreviewImageDto(id,product,image);
		}
				
	}
}
