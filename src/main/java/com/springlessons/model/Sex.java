package com.springlessons.model;

public enum Sex {
	MALE("Male"),
	FEM("Female");
	
	private String description;
	
	Sex(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

}
