package com.example.OnlineShop.SecurityConfig.JWT;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.OnlineShop.SecurityConfig.UserPrincipal;

@Component
public class JWTconverter {

	public UserPrincipal convert(DecodedJWT jwt) {
		
		List <SimpleGrantedAuthority> roles=null;
		var claim = jwt.getClaim("a");
		if (claim.isNull()) roles= List.of();
		else roles =claim.asList(SimpleGrantedAuthority.class);
		
		
		return new UserPrincipal(Long.valueOf(jwt.getSubject()),
				jwt.getClaim("l").asString(),
				roles);
	}
}
