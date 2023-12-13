package com.example.OnlineShop.SecurityConfig.JWT;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTdecoder {

	private final JWTproperties properties;
	
	public JWTdecoder(JWTproperties properties) {
		this.properties=properties;
	}
	
	public DecodedJWT decode(String token) {
		return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
				.build()
				.verify(token);
		
	}
	
	public boolean checkExpired(String token) {
		
		DecodedJWT jwt =decode(token);
		Date expDate = jwt.getExpiresAt();
		System.out.println(expDate);		
		Date now = new Date();
		System.out.println(now);
		if(expDate.before(now)) {
			return true;
		}
		return true;
	}
}
