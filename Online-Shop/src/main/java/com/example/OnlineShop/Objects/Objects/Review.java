package com.example.OnlineShop.Objects.Objects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineShop.Database.Dtos.ReviewDto;
import com.example.OnlineShop.Database.Models.ReviewModel;
import com.example.OnlineShop.Database.Repositories.ProductRepository;
import com.example.OnlineShop.Database.Repositories.ReviewRepository;
import com.example.OnlineShop.Database.Repositories.UserRepository;
import com.example.OnlineShop.Objects.Services.ReviewService;

@Service
public class Review implements ReviewService{

	private ReviewRepository reviewRepo;
	private UserRepository userRepo;
	private ProductRepository productRepo;
	
	@Autowired
	public Review (ReviewRepository reviewRepo,UserRepository userRepo,ProductRepository productRepo) {
		this.reviewRepo=reviewRepo;
		this.userRepo=userRepo;
		this.productRepo=productRepo;
	}
	
	@Override
	public ReviewDto addReview(ReviewDto reviewDto) {
		
		ReviewDto response=null;
		ReviewModel review=new ReviewModel();
		review.setUser(userRepo.findById(reviewDto.userId()).orElse(null));
		review.setProduct(productRepo.findById(reviewDto.productId()).orElse(null));
		
		review.setRating(reviewDto.rating());
		review.setContent(reviewDto.content());
		
		if (review.getUser()!=null&&review.getProduct()!=null) {
			ReviewModel result =reviewRepo.save(review);
		
			if (result!=null) {
				response= new ReviewDto.Builder()
						.id(result.getId())
						.userId(result.getUser().getId())
						.productId(0l)
						.userName(result.getUser().getLogin())
						.productName(null)
						.content(result.getContent())
						.rating(result.getRating())
						.build();
			}
		}
		return response;
	}

	@Override
	public boolean editReview(ReviewDto reviewDto) {
		boolean isSuccessfull=false;
		
		ReviewModel review=reviewRepo.findById(reviewDto.id()).orElse(null);
		
		if (review!=null) {
			
			review.setRating(reviewDto.rating());
			review.setContent(reviewDto.content());

			ReviewModel result =reviewRepo.save(review);
			if (result!=null) isSuccessfull=true;
		}
		
		return isSuccessfull;
	}

	@Override
	public boolean removeReview(Long id) {
		boolean isSuccessfull=false;
		
		 try {
		        reviewRepo.deleteById(id);
		        isSuccessfull = true;
		    } catch (Exception e) {		       
		        e.printStackTrace(); 
		    }
		
		return isSuccessfull;
	}

	@Override
	public List<ReviewDto> getReviewsForProduct(Long productId) {
		List<ReviewDto> list=reviewRepo.findDtoByProduct_id(productId);
		return list;
	}

	@Override
	public List<ReviewDto> getReviewsForUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewDto getReview(Long userId, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
