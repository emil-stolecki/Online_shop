package com.example.OnlineShop.Database.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*
 * This table associates images with a product they advertise
 * One product can use many images
 * 
 * 
 * */
@Entity(name="preview_images")
public class PreviewImageModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	
	
	@ManyToOne
	@JoinColumn(name="product_id",referencedColumnName="id")
	private ProductModel product;
	
	
	
	public Long getId() {
		return id;
	}

	public ProductModel getProductId() {
		return product;
	}

	public void setProductId(ProductModel productId) {
		this.product = productId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
