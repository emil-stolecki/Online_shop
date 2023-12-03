package com.example.OnlineShop.Database.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.OnlineShop.Database.Dtos.ReviewDto;
import com.example.OnlineShop.Database.Models.ReviewModel;


public interface ReviewRepository extends JpaRepository<ReviewModel,Long>{

	Optional<ReviewModel> findById(Long id);
	
	String dto="com.example.OnlineShop.Database.Dtos.ReviewDto";
	
	@Query(value = "SELECT new "+dto
			+"(r.id,r.user.id,r.user.login,r.product.id,r.product.name,r.rating,r.content) "
			+ "FROM reviews r WHERE r.user.id = :id")
	List<ReviewDto> findDtoByUser_id(@Param("id")Long id);
	@Query(value = "SELECT new "+dto
			+"(r.id,r.user.id,r.user.login,r.product.id,r.product.name,r.rating,r.content) "
			+ "FROM reviews r WHERE r.product.id = :id")
	List<ReviewDto> findDtoByProduct_id(@Param("id")Long id);
	
	@Query(value = "SELECT new "+dto
			+"(r.id,r.user.id,r.user.login,r.product.id,r.product.name,r.rating,r.content) "
			+ "FROM reviews r WHERE r.product.id = :id AND r.rating BETWEEN :minRating AND :maxRating")
	List<ReviewDto> findDtoByProduct_idAndRatingBetween(Long id, int minRating, int maxRating);
	
	
}
