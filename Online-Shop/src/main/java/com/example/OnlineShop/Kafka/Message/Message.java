package com.example.OnlineShop.Kafka.Message;

import java.util.List;

public record Message(
						Long productId,
						List<String> categories, 
						double price, 
						Long amountOfVisits,//delete 					
						Long amountBought) {
	
	
	public static class Builder {
		
		private Long productId;
		private List<String> categories; 
		private double price;
		private Long amountOfVisits;  
		private Long amountBought;
		

		public Builder productId(Long productId) {
			this.productId=productId;
			return this;
		}
		
		public Builder categories(List<String> cathegories) {
			this.categories=cathegories;
			return this;
		}
		public Builder price(double d) {
			this.price=d;
			return this;
		}
		public Builder amountOfVisits(Long amountOfVisits) {
			this.amountOfVisits=amountOfVisits;
			return this;
		}
	
		public Builder amountBought(long amountBought) {
			this.amountBought=amountBought;
			return this;
		}
		
		public Message build() {
			return new Message(productId, categories,price, amountOfVisits, amountBought);
		}
	}

	
}
