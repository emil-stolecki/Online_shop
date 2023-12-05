package com.example.OnlineShop.Database.Dtos;


public record ReviewDto(Long id,
						Long userId,
						String userName,
						Long productId,
						String productName,
						int rating,
						String content) {


	
	public static class Builder {
		
		private Long id;
		private Long userId;
		private String userName;
		private Long productId;
		private String productName;
		private int rating;
		private String content;
		
		
		public Builder id(Long id) {
            this.id = id;
            return this;
        }
		public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }
		public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }
		public Builder product(Long productId) {
            this.productId = productId;
            return this;
        }
		public Builder product(String productName) {
            this.productName = productName;
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
			return new ReviewDto(id,userId,userName,productId,productName,rating,content);
		}
	}
}
