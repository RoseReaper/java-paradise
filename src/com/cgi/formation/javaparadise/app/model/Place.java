package com.cgi.formation.javaparadise.app.model;

public class Place {
	
	private Long id;
	private String name;
	
	public Place() {
		
	}
	
	public Place(String name) {
		this.name = name;
	}
	
	public Place(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
	
	@Override
	public String toString() {
		return name;
	}

}
