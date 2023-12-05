package com.example.OnlineShop.Objects.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;
import com.example.OnlineShop.Database.Models.ReviewModel;
import com.example.OnlineShop.Database.Models.RoleModel;
import com.example.OnlineShop.Database.Models.UserModel;
import com.example.OnlineShop.Database.Repositories.CartRepository;
import com.example.OnlineShop.Database.Repositories.ProductRepository;
import com.example.OnlineShop.Database.Repositories.RoleRepository;
import com.example.OnlineShop.Database.Repositories.UserRepository;
import com.example.OnlineShop.Objects.Services.UserService;

@Service
public class User implements UserService{

	private UserRepository userRepo;
	private RoleRepository roleRepo;
	@Autowired
	public User(UserRepository userRepo,RoleRepository roleRepo) {
		
		this.userRepo=userRepo;
		this.roleRepo=roleRepo;
	
	}
	
	@Override
	public boolean register(UserRegistrationDto registration) {
		boolean isSuccessfull=false;
		
		UserModel user =new UserModel();
		
		user.setLogin(registration.login());
		user.setFirstName(registration.firstName());
		user.setLastName(registration.lastName());
		user.setEncryptedPassword(registration.password());
		user.setEmail(registration.email());		
		RoleModel role= roleRepo.findByName("USER").orElse(null);
		user.setRole(role);
		UserModel result = userRepo.save(user);
		if (result!=null)isSuccessfull=true;
			
		return isSuccessfull;
	}

	@Override
	public boolean updateProfile(UserDto userEdited) {
		boolean isSuccessfull=false;
		
		UserModel user=userRepo.findById(userEdited.id()).orElse(null);
		
		if(user!=null) {
			if(userEdited.login()!=null) user.setLogin(userEdited.login());
			if(userEdited.firstName()!=null) user.setFirstName(userEdited.firstName());
			if(userEdited.lastName()!=null) user.setLastName(userEdited.lastName());
			if(userEdited.email()!=null) user.setEmail(userEdited.email());
			if(userEdited.encryptedPassword()!=null) user.setEncryptedPassword(userEdited.encryptedPassword());
		
			UserModel result=userRepo.save(user);
			if (result!=null)isSuccessfull=true;
		}
		
		

		return isSuccessfull;
	}

	@Override
	public boolean deleteprofile(Long id) {
		boolean isSuccessfull=false;
		
		 try {
		        userRepo.deleteById(id);
		        isSuccessfull = true;
		    } catch (Exception e) {		       
		        e.printStackTrace(); 
		    }
		
		return isSuccessfull;
	}

	@Override
	public UserDto getUserDetails(long id) {
		UserDto user=userRepo.findDtoById(id);
		return user;
	}
	
	

}
