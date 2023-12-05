package com.example.OnlineShop.Database.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineShop.Database.Dtos.UserDto;
import com.example.OnlineShop.Database.Dtos.UserLoginDto;
import com.example.OnlineShop.Database.Models.UserModel;



public interface UserRepository extends JpaRepository<UserModel,Long>{
	
	
	String dto1="com.example.OnlineShop.Database.Dtos.UserDto";
	String dto2="com.example.OnlineShop.Database.Dtos.UserLoginDto";
	//find by login and find by email will be used for authentication
	@Query(value = "SELECT new "+dto2+"(u.login,u.email,u.encryptedPassword) FROM users u WHERE u.login = :login")
	Optional<UserLoginDto> findDtoByLogin(@Param("login")String login);
	@Query(value = "SELECT new "+dto2+"(u.login,u.email,u.encryptedPassword) FROM users u WHERE u.email = :email")
	Optional<UserLoginDto> findDtoByEmail(@Param("email")String email);
	
	//find by id returns the entire record
	@Query(value = "SELECT new "+dto1+
			"(u.id,u.login,u.email,u.firstName,u.lastName,u.encryptedPassword,u.joined,u.role.id) FROM users u WHERE u.id = :id")
	UserDto findDtoById(@Param("id")Long id);
	
	Optional<UserModel> findById(Long id);
}
