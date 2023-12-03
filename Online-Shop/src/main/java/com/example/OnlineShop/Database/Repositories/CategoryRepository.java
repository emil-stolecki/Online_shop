package com.example.OnlineShop.Database.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineShop.Database.Models.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel,Long>{


	Optional<CategoryModel> findById(Long id);
	
	
	Optional<CategoryModel> findByName(String name);
}
