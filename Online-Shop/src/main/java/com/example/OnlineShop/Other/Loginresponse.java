package com.example.OnlineShop.Other;


public class Loginresponse {
	private boolean isSuccessfull;
    private String token;
    
    
	public Loginresponse(boolean isSuccessfull,String token) {	
		this.isSuccessfull = isSuccessfull;
		this.token=token;
	}


	public boolean isSuccessfull() {
		return isSuccessfull;
	}

	
	public String getToken() {
		return token;
	}

}
