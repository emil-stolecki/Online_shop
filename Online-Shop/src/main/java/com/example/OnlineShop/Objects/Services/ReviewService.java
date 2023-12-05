package com.example.OnlineShop.Objects.Services;

import java.util.List;

import com.example.OnlineShop.Database.Dtos.ReviewDto;

public interface ReviewService {

	//getReview: user,product -> ???review
	
	boolean addReview(ReviewDto reviewDto);
	boolean editReview(ReviewDto reviewDto);
	boolean removeReview(Long id);
	List<ReviewDto> getReviewsForProduct(Long productId);
	List<ReviewDto> getReviewsForUser(Long userId);
	
	ReviewDto getReview(Long userId,Long productId);
	 
}
