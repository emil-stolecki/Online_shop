package com.example.OnlineShop.SecurityConfig;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.OnlineShop.Database.Dtos.AuthenticatedUserDto;
import com.example.OnlineShop.Database.Dtos.UserCheckLoginDto;
import com.example.OnlineShop.Database.Repositories.UserRepository;



@Service
public class UserDetailsConfig implements UserDetailsService{
	private UserRepository userRepo;
	@Autowired
	public UserDetailsConfig(UserRepository userRepo) {
		
		this.userRepo = userRepo;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCheckLoginDto user;
		if(username.contains("@")) {
			user = userRepo.findLoginDtoByEmail(username).orElseThrow(null);
			}
		else {
			
			user = userRepo.findLoginDtoByLogin(username).orElseThrow(null);
			
		}
		if(user!= null) {
			
			User authUser= new User(
					
					user.login(),
					user.password(),
					new ArrayList<String>().stream().map((role)->new SimpleGrantedAuthority(""))
					.collect(Collectors.toList())
			);
			return authUser;		
		}
		else {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
	}
}
