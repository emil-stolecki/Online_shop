package com.example.OnlineShop.Database.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
 * This Entity represents the user of the application
 * When a new user creates an account, a new record is created.
 * It holds data needed to authenticate the user when they are logging in
 * and identify the user when they are buying items and leaving reviews
 * 
 * It links the user with their role: 
 * * a regular user/consumer or a member of the staff - a moderator or an admin
 * * or a banned user with limited permits.
 * 
 * One User can have only one role at the time.
 * 
 * 
 * It links the user with their shopping cart and items in it.
 * One User can have many items in their cart 
 * Many Users can have the same item in their carts (as long as they are in stock)
 * 
 */

@Entity(name="users")
public class UserModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true) 
	private String login;
	
	@Column(unique=true) 
	private String email;
	
	private String firstName;
	private String lastName;
	
	private String encryptedPassword;
	
	@CreationTimestamp
	private LocalDateTime joined;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="role_id")
	private RoleModel role=new RoleModel();
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<ItemInCartModel> items=new ArrayList<>();
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<ReviewModel> reviews=new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public LocalDateTime getJoined() {
		return joined;
	}
	public void setJoined(LocalDateTime joined) {
		this.joined = joined;
	}
	public RoleModel getRole() {
		return role;
	}
	public void setProducts(RoleModel role) {
		this.role = role;
	}
	public List<ItemInCartModel> getItems() {
		return items;
	}
	public void setItems(List<ItemInCartModel> items) {
		this.items = items;
	}
	public List<ReviewModel> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
	
	
	
}
