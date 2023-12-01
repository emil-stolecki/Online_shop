package com.example.OnlineShop.Database.Models;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;


/*
 * This entity links the users table and the products table
 * Each record consists of one user's id and one product's id and the number of this product in cart
 * User can have many products  of different type in cart
 * The same product can be put in a cart by many users (as long as it's in stock)
 * 
 * */

@Entity(name="items_in_carts")
public class ItemInCartModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",referencedColumnName="id")
	@NotNull
	private UserModel user;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="product_id",referencedColumnName="id")
	@NotNull
	private ProductModel product;
	
	private int amount;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserModel getUserId() {
		return user;
	}
	public void setUser (UserModel user ) {
		this.user  = user ;
	}
	public ProductModel getProduct () {
		return product ;
	}
	public void setProduct (ProductModel product ) {
		this.product  = product ;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}