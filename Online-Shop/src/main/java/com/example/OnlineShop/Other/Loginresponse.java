package com.example.OnlineShop.Other;


public class Loginresponse {
	private boolean isSuccessfull;
    private String token;
    private Long userId;
    
    
	public Loginresponse(boolean isSuccessfull,String token, Long userId) {	
		this.isSuccessfull = isSuccessfull;
		this.token=token;
		this.userId=userId;
	}


	public boolean isSuccessfull() {
		return isSuccessfull;
	}

	
	public String getToken() {
		return token;
	}
	public Long getId() {
		return userId;
	}

}
