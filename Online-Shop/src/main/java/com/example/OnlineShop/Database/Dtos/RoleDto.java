package com.example.OnlineShop.Database.Dtos;

public record RoleDto(Long id, String name) {
	
	public static class Builder {
		private Long id;
		private String name;
		
		public Builder id(Long id) {
			this.id=id;
			return this;
		}
		
		public Builder name(String name) {
			this.name=name;
			return this;
		}
		
		
		public RoleDto build() {
			return new RoleDto(id,name);
		}
	}

}
