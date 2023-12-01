package com.springlessons.model;

public enum Category {
	CEL("Celphones"),
	ELETRO("Eletronics"),
	INFO("Informatic"),
	FORNI("Forniture");
	
	private String description;
	
	Category(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
}
