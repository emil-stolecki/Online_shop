package com.example.OnlineShop.Database.Dtos;

public record CategoryDto(Long id, String name) {

	public class Builder{
		private long id;
		private String name;
		
		
		public Builder id(long id) {
			this.id=id;
			return this;
		}
		
		public Builder name(String name) {
			this.name=name;
			return this;
		}
		
		public CategoryDto build() {
			return new CategoryDto(id,name);
		}
	}
}
