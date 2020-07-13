package com.cgi.formation.javaparadise.app.model;

public class Trip {
	
	private Long id;
	private Place departure;
	private Place arrival;
	private Float price;
	
	public Trip() {
		
	}
	
	public Trip(Place departure, Place arrival, Float price) {
		this.departure = departure;
		this.arrival = arrival;
		this.price = price;
	}
	
	public Trip(Long id, Place departure, Place arrival, Float price) {
		this.id = id;
		this.departure = departure;
		this.arrival = arrival;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Place getDeparture() {
		return departure;
	}
	
	public void setDeparture(Place departure) {
		this.departure = departure;
	}
	
	public Place getArrival() {
		return arrival;
	}
	
	public void setArrival(Place arrival) {
		this.arrival = arrival;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}

}
