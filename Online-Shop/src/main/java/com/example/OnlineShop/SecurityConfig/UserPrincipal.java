package com.example.OnlineShop.SecurityConfig;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails{

	

	private final Long userId;
	private final String login;
	private final Collection<? extends GrantedAuthority> roles;
	@JsonIgnore
	private final String password;
	

	public UserPrincipal(Long userId,String login, String password,Collection<? extends GrantedAuthority> roles ) {
		this.userId=userId;
		this.login = login;
		this.password = password;
		this.roles=roles;
	}
	public UserPrincipal(Long userId,String login,Collection<? extends GrantedAuthority> roles ) {
		this.userId=userId;
		this.login = login;
		this.password ="";
		this.roles=roles;
	}

	public Long getId() {
		return userId;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles;
	}
	
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
