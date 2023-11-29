package com.example.OnlineShop.Database.Models;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;


@Entity(name="products_in_carts")
public class CartModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName="id")
	@NotNull
	private UserModel userId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id",referencedColumnName="id")
	@NotNull
	private ProductModel productId;
	private int amount;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}