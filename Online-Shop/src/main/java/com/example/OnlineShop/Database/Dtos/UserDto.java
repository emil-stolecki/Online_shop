package com.example.OnlineShop.Database.Dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.example.OnlineShop.Database.Models.ItemInCartModel;
import com.example.OnlineShop.Database.Models.ReviewModel;
import com.example.OnlineShop.Database.Models.RoleModel;


public record UserDto(Long id,
					 String login,
					 String email,
					 String firstName,
					 String lastName,
					 String encryptedPassword,
					 LocalDateTime joined,
					 RoleModel role,
					 List<ItemInCartModel> items,
					 List<ReviewModel> reviews) {
	
	
	
	public static class Builder{
		
		private Long id;
		private String login;
		private String email;
		private String firstName;
		private String lastName;
		private String encryptedPassword;
		private LocalDateTime joined;
		private RoleModel role;
		private List<ItemInCartModel> items;
		private List<ReviewModel> reviews;
		
		public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }
        public Builder email(String email) {
            this.email=email;
            return this;
        }
        public Builder firstName(String firstName) {
            this.firstName=firstName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.lastName=lastName;
            return this;
        }
        public Builder password(String password) {
            this.encryptedPassword=password;
            return this;
        }
        public Builder joined(LocalDateTime joined) {
            this.joined=joined;
            return this;
        }
        public Builder roles(RoleModel role) {
            this.role=role;
            return this;
        }
        public Builder items(List<ItemInCartModel> items) {
        	this.items=items;
        	return this;
        }
        public Builder reviews(List<ReviewModel> reviews) {
        	this.reviews=reviews;
        	return this;
        }
        
        public UserDto build() {
            return new UserDto(id,login,email,
            				   firstName,lastName,
            				   encryptedPassword,
            				   joined,role,
            				   items,reviews);
        }
		
	}

}
