package com.example.OnlineShop.SecurityConfig.JWT;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTissuer {

	
	private final JWTproperties properties;
	
	
	public JWTissuer(JWTproperties properties) {
		this.properties = properties;
	}


	public String issue(long userId, String login, Collection<? extends GrantedAuthority> role) {
		return JWT.create()
				.withSubject(String.valueOf(userId))
				.withExpiresAt(Date.from(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS))))
				.withClaim("l", login)
				.withClaim("i", userId)
				.sign(Algorithm.HMAC256(properties.getSecretKey()));
	}
}
