package com.example.OnlineShop.Database.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.OnlineShop.Database.Dtos.UserLoginDto;
import com.example.OnlineShop.Database.Dtos.UserRegistrationDto;
import com.example.OnlineShop.Database.Models.RoleModel;
import com.example.OnlineShop.Database.Models.UserModel;

import com.example.OnlineShop.Database.Dtos.UserDto;
import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/import.sql")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Test
	@Transactional
	public void findDtoByLoginTest() {
		String login="john";
		Optional<UserLoginDto> optuser= userRepo.findDtoByLogin(login);
		UserLoginDto user=optuser.orElse(null);
		System.out.println(user);
		assertThat(user).isNotNull()
		   .hasFieldOrPropertyWithValue("login", login)
		   .hasFieldOrPropertyWithValue("email", "john@gmail.com")
		   .hasFieldOrPropertyWithValue("encryptedPassword", "dsbfjdsfg");
		
	}
	
	@Test
	@Transactional
	public void findDtoByEmailTest() {
		String email="annn@gmail.com";
		Optional<UserLoginDto> optuser= userRepo.findDtoByEmail(email);
		UserLoginDto user=optuser.orElse(null);
		System.out.println(user);
		assertThat(user).isNotNull()
		   .hasFieldOrPropertyWithValue("login", "ann")
		   .hasFieldOrPropertyWithValue("email", email)
		   .hasFieldOrPropertyWithValue("encryptedPassword", "hfhgfhhgf");
		
	}
	
	@Test
	@Transactional
	public void findByIdTest() {
		Long id=3L;
		UserModel user= userRepo.findById(id).orElse(null);
		
		assertThat(user).isNotNull()
		   .hasFieldOrPropertyWithValue("login", "steve")
		   .hasFieldOrPropertyWithValue("email", "steve@gmail.com")
		   .hasFieldOrPropertyWithValue("firstName", "Steve")
		   .hasFieldOrPropertyWithValue("lastName", "Fox")
		   .hasFieldOrPropertyWithValue("encryptedPassword", "ertertas");
		
		
		
	}
	
	@Test
	@Transactional
	public void saveAndFindByIdTest() {

		UserModel user = new UserModel();
		user.setLogin("adam");
		user.setEmail("adam@gmail.com");
		user.setFirstName("Adam");
		user.setLastName("Kowalski");
		user.setEncryptedPassword("sdfbkuysdgfkjsdh");
		
		RoleModel role= roleRepo.findByName("USER").orElse(null);

		
		user.setRole(role);
		
		 UserModel savedUser=userRepo.save(user);		 
		 assertThat(savedUser.getId()).isNotNull();
		 
		 UserModel retrievedUser = userRepo.findById(savedUser.getId()).orElse(null);
	        assertThat(retrievedUser).isNotNull();
	        assertThat(retrievedUser.getLogin()).isEqualTo(user.getLogin());
	        
	}
	
}
