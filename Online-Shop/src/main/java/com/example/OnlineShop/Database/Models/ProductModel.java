package com.example.OnlineShop.Database.Models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="products")
public class ProductModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private int amountInStock;
	private float price;
	@ManyToOne
	@JoinColumn(name="seller_id")
	private UserModel seller;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public UserModel getSeller() {
		return seller;
	}
	public void setSeller(UserModel seller) {
		this.seller = seller;
	}
	
	
	

}
