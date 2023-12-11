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

import com.example.OnlineShop.Database.Dtos.UserLoginDto;
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
		UserLoginDto user;
		if(username.contains("@")) {			
			user = userRepo.findDtoByEmail(username).orElse(null);
			}
		else {
			System.out.println("here");
			System.out.println(username);
			user = userRepo.findDtoByLogin(username).orElse(null);
			System.out.println(user);
		}
		if(user!= null) {
			
			User authUser= new User(
					
					user.login(),
					user.encryptedPassword(),
					new ArrayList<String>().stream().map((role)->new SimpleGrantedAuthority(""))
					.collect(Collectors.toList())
			
			);		
			System.out.println(authUser.getUsername());
			return authUser;		
		}
		else {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		
	}
}
