package com.cgi.formation.javaparadise.app.dao;

import java.util.List;

import com.cgi.formation.javaparadise.app.exception.EntityNotFoundException;
import com.cgi.formation.javaparadise.app.model.Place;

public interface PlaceDao {
	
	public Long createPlace(Place place);
	
	public Place findPlaceById(Long id) throws EntityNotFoundException;
	
	public boolean findIfPlaceHasTrips(Long placeId) throws EntityNotFoundException;
	
	public boolean updatePlace(Place place);
	
	public boolean removePlace(Place place);
	
	public List<Place> findAllPlaces();


}
