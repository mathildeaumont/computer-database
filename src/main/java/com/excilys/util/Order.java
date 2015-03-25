package com.excilys.util;

public enum Order {
	NAME("name"),
	INTRODUCED("introduced"),
	DISCONTINUED("discontinued"),
	COMPANY("company_id");
	
	private String order;
	
	private Order(String order) {
		this.order = order;
	}
	
	public String getOrder() {
		return order;
	}
}
