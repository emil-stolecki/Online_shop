package com.example.OnlineShop.Database.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;

/*
 * This entity represents a product than can be bought using the app
 * It holds information that is relevant for the customer
 * It is linked to a seller
 * It keeps track of how much items is left in stock
 * 
 * Price and amount in stock could be potentially moved to another table, 
 * leaving this table mostly read-only, assuming description and name won't change very often
 * 
 * 
 * */


//Add field for images
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/*
 * This is a table of all products that can be bought in the application
 * It holds information relevant to the potential customer
 * It allows to identify to the seller
 * 
 * Price and Amount in Stock could be moved to another table
 * to make this table mostly read-only 
 * assuming description and name of the product won't change very often
 * 
 * Each product can have a couple of images that will be displayed on the product page
 * */

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity(name="products")
public class ProductModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	
	@Column(name="amount_in_stock")
	private int amountInStock;
	private double price;
	
	private String seller;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			  name = "products_categories", 
			  joinColumns = @JoinColumn(name = "product_id"), 
			  inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CategoryModel> categories=new ArrayList<CategoryModel>();
	
	@JsonBackReference
	@OneToMany(mappedBy="product", fetch = FetchType.LAZY)
	private List<PreviewImageModel> images = new ArrayList<PreviewImageModel>();
	
	@JsonBackReference
	@OneToMany(mappedBy="product", fetch = FetchType.LAZY)
	private List<ReviewModel> reviews=new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmountInStock() {
		return amountInStock;
	}
	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public List<CategoryModel> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryModel> categories) {
		this.categories = categories;
	}
	public List<PreviewImageModel> getImages() {
		return images;
	}
	public void setImages(List<PreviewImageModel> images) {
		this.images = images;
	}

	public List<ReviewModel> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}
	
	
	

}
