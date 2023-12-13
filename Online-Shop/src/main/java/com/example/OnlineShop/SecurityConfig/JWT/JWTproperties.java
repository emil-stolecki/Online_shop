package com.example.OnlineShop.SecurityConfig.JWT;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("security.jwt")
public class JWTproperties {

	private String secretkey;
	
	public String getSecretKey() {
		return secretkey;
	}
	public void setSecretKey(String secretkey) {
		this.secretkey=secretkey;
	}
	
}
