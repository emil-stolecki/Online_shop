package com.example.OnlineShop.Kafka.Message;

import java.util.List;


public record ViewedProduct (
							Long productId,
							int amountBoughtSoFar,
							List<String> cathegories, 
							double price
							){

	
	public static class Builder{
		
		private Long productId;
		private int amountBoughtSoFar;
		private List<String> cathegories;
		private double price;
		
		public Builder productId(Long productId) {
			this.productId=productId;
			return this;
		}
		
		public Builder amountBoughtSoFar(int amountBoughtSoFar) {
			this.amountBoughtSoFar=amountBoughtSoFar;
			return this;
		}
		
		public Builder cathegories(List<String> cathegories) {
			this.cathegories=cathegories;
			return this;
		}
		
		public Builder price(double price) {
			this.price=price;
			return this;
		}
		
		public ViewedProduct build() {
			return new ViewedProduct(productId,amountBoughtSoFar,cathegories,price);
		}
	}
}
