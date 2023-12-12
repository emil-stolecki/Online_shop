package com.example.OnlineShop.Other;

import com.example.OnlineShop.Database.Dtos.AuthenticatedUserDto;

public class Loginresponse {
	private boolean isSuccessfull;
    private AuthenticatedUserDto user;
    
    
	public Loginresponse(boolean isSuccessfull, AuthenticatedUserDto user) {	
		this.isSuccessfull = isSuccessfull;
		this.user = user;
	}


	public boolean isSuccessfull() {
		return isSuccessfull;
	}

	public AuthenticatedUserDto getUser() {
		return user;
	}

}
