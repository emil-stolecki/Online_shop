package com.example.OnlineShop.Database.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*
 * This table stores reviews
 * user can leave only one review for each product (review can be edited)
 * 
 * */
@Entity(name="reviews")
public class ReviewModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="id")
	private UserModel userId;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="product_id",referencedColumnName="id")
	private ProductModel productId;
	
	
	private int rating;//0-10
	private String content;
	
	
	public Long getId() {
		return id;
	}
	
	
	public UserModel getUserId() {
		return userId;
	}


	public void setUserId(UserModel userId) {
		this.userId = userId;
	}


	public ProductModel getProductId() {
		return productId;
	}


	public void setProductId(ProductModel productId) {
		this.productId = productId;
	}


	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
