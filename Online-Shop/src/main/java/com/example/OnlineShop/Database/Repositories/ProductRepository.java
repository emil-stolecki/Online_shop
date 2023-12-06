package com.example.OnlineShop.Database.Repositories;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineShop.Database.Dtos.ProductDto;
import com.example.OnlineShop.Database.Dtos.ProductLessDto;
import com.example.OnlineShop.Database.Models.PreviewImageModel;
import com.example.OnlineShop.Database.Models.ProductModel;
import com.example.OnlineShop.Database.Models.ReviewModel;
import com.example.OnlineShop.Database.Models.UserModel;


public interface ProductRepository extends JpaRepository<ProductModel,Long>{

	
	
	
	Optional<ProductModel> findById(Long id);
	

	//Will be used in search results, displayed to the user as list	
	String dtoLess="com.example.OnlineShop.Database.Dtos.ProductLessDto";
	
	@Query(value = "SELECT DISTINCT new "+dtoLess+"(p.id,p.name,p.price,p.seller) FROM products p JOIN p.categories c "
			+ "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) "
			+ "AND LOWER(c.name) LIKE LOWER(:category) "
			+ "AND p.price BETWEEN :minPrice AND :maxPrice")
			
	List<ProductLessDto> findDtoByFilter( @Param("name")String name,
										  @Param("category")String category,
										  @Param("minPrice") double minPrice,
										  @Param("maxPrice") double maxPrice,
										  Pageable pageable);
										
	
	
	@Query("SELECT new "+dtoLess+"(p.id, p.name, p.price, p.seller) " 
		       			+"FROM products p WHERE p.id IN :ids")	
	List<ProductLessDto> findAllDtosByIdIn(@Param("ids")List<Long> ids);
	
	@Query(value = "SELECT COUNT(DISTINCT p.id) FROM products p JOIN p.categories c "
	        + "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) "
	        + "AND LOWER(c.name) LIKE LOWER(:category) "
	        + "AND p.price BETWEEN :minPrice AND :maxPrice")
	Long countByFilter(@Param("name") String name,
	                   @Param("category") String category,
	                   @Param("minPrice") double minPrice,
	                   @Param("maxPrice") double maxPrice);
	
}

 