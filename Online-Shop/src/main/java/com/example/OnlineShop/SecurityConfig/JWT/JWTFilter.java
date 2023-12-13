package com.example.OnlineShop.SecurityConfig.JWT;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JWTFilter extends OncePerRequestFilter{

	
	private final JWTdecoder decoder;
	private final JWTconverter converter;
	
	
	
	public JWTFilter(JWTdecoder decoder, JWTconverter converter) {
		this.decoder = decoder;
		this.converter = converter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		extractToken(request)
		.map(decoder::decode)
		.map(converter::convert)
		.map(UserAuthenticationToken::new)
		.ifPresent(auth-> SecurityContextHolder.getContext().setAuthentication(auth));
		
		filterChain.doFilter(request, response);
	}

	private Optional<String> extractToken(HttpServletRequest request){
		var token = request.getHeader("Authorization");
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return Optional.of(token.substring(7));
		}
		return Optional.empty();
	}
}
