package com.example.OnlineShop.Database.Dtos;


public record ReviewDto(Long id,
						Long userId,
						String userName,
						Long productId,
						String productName,
						int rating,
						String content) {

/*
	
	public static class Builder {
		
		private Long id;
		private UserModel user;
		private ProductModel product;
		private int rating;
		private String content;
		
		
		public Builder id(Long id) {
            this.id = id;
            return this;
        }
		public Builder user(UserModel user) {
            this.user = user;
            return this;
        }
		public Builder product(ProductModel product) {
            this.product = product;
            return this;
        }
		public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }
		public Builder content(String content) {
            this.content = content;
            return this;
        }
		
		public ReviewDto build() {
			return new ReviewDto(id,user,product,rating,content);
		}
	}*/
}
