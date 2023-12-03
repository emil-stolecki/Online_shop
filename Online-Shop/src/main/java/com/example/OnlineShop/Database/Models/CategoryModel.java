package com.example.OnlineShop.Database.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/*
 * This is a list of categories of products
 * There can be many products from each category
 * One product can have more than one category
 * */
@Entity(name="categories")
public class CategoryModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy="categories", fetch = FetchType.LAZY)
	private List<ProductModel> products = new ArrayList<ProductModel>();

	
	
	
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

	public List<ProductModel> getProducts() {
		return products;
	}


	
	
}
