package com.example.OnlineShop.Database.Repositories;

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

	
	String dto="com.example.OnlineShop.Database.Dtos.ProductDto";
	String dtoLess="com.example.OnlineShop.Database.Dtos.ProductLessDto";
	
	Optional<ProductModel> findById(Long id);
	
	@Query(value = "SELECT new "+dto+"(p.id,p.name,p.description,p.amountInStock,p.price,p.seller,p.categories, p.images, p.reviews) FROM products p WHERE p.id = :id")
	ProductDto findDtoById(@Param("id")Long id);
	
	//Will be used in search results, displayed to the user as list
	
	@Query(value = "SELECT new "+dtoLess+"(p.id,p.name,p.price,p.seller) FROM products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<ProductLessDto> findDtoByName(@Param("name")String name);
	
	@Query(value = "SELECT new "+dtoLess+"(p.id,p.name,p.price,p.seller) FROM products p JOIN p.categories c WHERE LOWER(c.name) LIKE LOWER(:name)")
	List<ProductLessDto> findDtoByCategory(@Param("name")String name);
	
	@Query(value = "SELECT new "+dtoLess+"(p.id,p.name,p.price,p.seller) FROM products p "
				+ "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))"
				+ "AND p.price BETWEEN :minPrice AND :maxPrice")
	List<ProductLessDto> findDtoByNameAndRange( @Param("name")String name,
												@Param("minPrice") float minPrice,
												@Param("maxPrice") float maxPrice);
	
	@Query(value = "SELECT new "+dtoLess+"(p.id,p.name,p.price,p.seller) FROM products p JOIN p.categories c "
				+ "WHERE LOWER(c.name) LIKE LOWER(:name)"
				+ "AND p.price BETWEEN :minPrice AND :maxPrice")
	List<ProductLessDto> findDtoByCategoryAndRange( @Param("name")String name,
													@Param("minPrice") float minPrice,
													@Param("maxPrice") float maxPrice);
	
	
	
}

 