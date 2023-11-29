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
import jakarta.persistence.OneToMany;

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
	@ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(
			name="user_roles",
			joinColumns= {@JoinColumn(name="user_id",referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName="id")}
			)
	private List<RoleModel> roles=new ArrayList<>();
	@OneToMany(mappedBy="userId")
    private List<CartModel> items=new ArrayList<>();
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<RoleModel> getRoles() {
		return roles;
	}
	public void setProducts(List<RoleModel> roles) {
		this.roles = roles;
	}
	
	
	
}
