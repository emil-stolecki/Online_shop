package com.example.OnlineShop.Database.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineShop.Database.Dtos.ItemInCartDto;
import com.example.OnlineShop.Database.Models.ItemInCartModel;

public interface CartRepository extends JpaRepository<ItemInCartModel,Long>{
	
	String dto="com.example.OnlineShop.Database.Dtos.ItemInCartDto";
	
	@Query(value ="SELECT new "+dto+"(c.id,c.product.id,c.product.name,c.amount) "
					+ "FROM items_in_carts c WHERE c.id = :id")
	Optional<ItemInCartDto> findDtoById(@Param("id")Long id);
	//Optional<ItemInCartModel> findByUser_IdAndProduct_Id(Long userId,Long productId);
	@Query(value ="SELECT new "+dto+"(c.id,c.product.id,c.product.name,c.amount) "
			+ "FROM items_in_carts c WHERE c.user.id = :user_id")
	List<ItemInCartDto> findDtoByUser_id(@Param("user_id")Long id);
	
	
	
}
