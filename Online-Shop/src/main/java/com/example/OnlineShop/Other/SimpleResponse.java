package com.example.OnlineShop.Other;

public class SimpleResponse {
	
	private boolean isSuccessfull;
    private String message;
    
    
	public SimpleResponse(boolean isSuccessfull, String message) {	
		this.isSuccessfull = isSuccessfull;
		this.message = message;
	}


	public boolean isSuccessfull() {
		return isSuccessfull;
	}

	public String getMessage() {
		return message;
	}

	
    
    
}
