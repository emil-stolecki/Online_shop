package com.example.OnlineShop.SecurityConfig.JWT;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.example.OnlineShop.SecurityConfig.UserPrincipal;


public class UserAuthenticationToken extends AbstractAuthenticationToken {

	private final UserPrincipal user;
	
	
	
	
	public UserAuthenticationToken(UserPrincipal user) {
		super(user.getAuthorities());
		this.user=user;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {		
		return null;
	}

	@Override
	public UserPrincipal getPrincipal() {
		return user;
	}

}
