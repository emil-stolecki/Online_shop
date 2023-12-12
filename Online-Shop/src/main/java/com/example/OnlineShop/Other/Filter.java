package com.example.OnlineShop.Other;

public class Filter {

	private Double minPrice;
	private Double maxPrice;
	private String category;
	private String name;
	private int offset;
	private int limit;
	
	
	public Filter() {
		this.minPrice = 0d;
		this.maxPrice = 10000d;
		this.category = "%";
		this.name     = null;
		this.offset   = 0;
		this.limit=10;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	
}
