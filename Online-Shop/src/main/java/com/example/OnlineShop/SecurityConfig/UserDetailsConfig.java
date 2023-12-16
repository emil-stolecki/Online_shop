package com.example.OnlineShop.SecurityConfig;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.OnlineShop.Database.Dtos.AuthenticatedUserDto;
import com.example.OnlineShop.Database.Dtos.UserCheckLoginDto;
import com.example.OnlineShop.Database.Repositories.UserRepository;
//import com.example.OnlineShop.Objects.Objects.User;



@Service
public class UserDetailsConfig implements UserDetailsService{
	//private User userService;
	private UserRepository userRepo;
	@Autowired
	public UserDetailsConfig(/*User userService,*/UserRepository userRepo) {
		this.userRepo=userRepo;
		//this.userService = userService;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCheckLoginDto user;
		if(username.contains("@")) {
			user = userRepo.findLoginDtoByEmail(username).orElse(null);
			
			}
		else {
			user = userRepo.findLoginDtoByLogin(username).orElse(null);
			
		}
		if(user!= null) {
			
			ArrayList<String> roles=new ArrayList<String>();
			roles.add(user.role());
			UserPrincipal authUser= new UserPrincipal(
					
					user.id(),
					user.login(),
					user.password(),
					roles.stream().map((role)->new SimpleGrantedAuthority(role))
					.collect(Collectors.toList())
			);
			return authUser;		
		}
		else {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
	}
}
