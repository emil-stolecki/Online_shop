package com.example.OnlineShop.Kafka.Message;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Product {

		@JsonProperty
		private Long amountBought;
		@JsonProperty
		private Long amountViewed;
		@JsonProperty
		private List<String> cathegories; 
		@JsonProperty
		private double price;
		
		
		
		public Product() {
		
			this.amountBought=0L;
			this.amountViewed=0L;
		}
		
		public Product update(Message message) {
			
			if(message.amountBought()!=null) amountBought=this.amountBought+(long)message.amountBought();
			if(message.amountOfVisits()!=null) amountViewed=this.amountViewed+(long)message.amountOfVisits();			
			if(message.categories()!=null) cathegories = message.categories();
			
			
			price=message.price();
			
			return this;
		}
		
		
}
		
	

