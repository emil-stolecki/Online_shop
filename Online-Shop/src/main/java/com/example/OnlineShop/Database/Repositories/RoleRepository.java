package com.example.OnlineShop.Database.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.OnlineShop.Database.Models.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel,Long>{

	
	
	Optional<RoleModel> findByName(String name);
	Optional<RoleModel> findById(Long id);
}
