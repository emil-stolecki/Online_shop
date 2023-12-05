package com.example.OnlineShop.Database.Dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto(	@NotEmpty @Size(min=4,max=20) 
									@Pattern(regexp="^[a-zA-Z0-9._-]+$")
									String login,
									@NotEmpty
									@Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
									String email,
									String firstName,
									String lastName,
									@NotEmpty
									@Size(min=8,max=32)
									@Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[._-]).*$")
									String password,
									String password2) {

	
//This is data from user's registration form to be sent to the server
}
