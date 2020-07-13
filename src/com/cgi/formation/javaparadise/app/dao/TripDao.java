package com.cgi.formation.javaparadise.app.dao;

import java.util.List;

import com.cgi.formation.javaparadise.app.exception.EntityNotFoundException;
import com.cgi.formation.javaparadise.app.model.Place;
import com.cgi.formation.javaparadise.app.model.Trip;

public interface TripDao {
	
	public Long createTrip(Trip trip);
	
	public Trip findTripById(Long id) throws EntityNotFoundException;
	
	public boolean removeTrip(Trip trip);

	public boolean removeAllTrips(Place place);
	
	public List<Trip> findAllTrips();


}
