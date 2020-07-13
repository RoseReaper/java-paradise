package com.cgi.formation.javaparadise.app;

import java.util.Scanner;

import com.cgi.formation.javaparadise.app.dao.PlaceDao;
import com.cgi.formation.javaparadise.app.dao.TripDao;
import com.cgi.formation.javaparadise.app.dao.jdbc.JdbcPlaceDao;
import com.cgi.formation.javaparadise.app.dao.jdbc.JdbcTripDao;
import com.cgi.formation.javaparadise.app.exception.EntityNotFoundException;
import com.cgi.formation.javaparadise.app.model.Place;
import com.cgi.formation.javaparadise.app.model.Trip;

public class Interaction {

	MenuConstants menuConstants = new MenuConstants();
	PlaceDao placeDao = new JdbcPlaceDao();
	TripDao tripDao = new JdbcTripDao();
	Scanner scanner = new Scanner(System.in);
	boolean quit = false;
	
	public void startInteraction() throws EntityNotFoundException {
		System.out.println(menuConstants.getWelcome());
		
		do {
		
		    System.out.println(menuConstants.getMainScreen());
		    String inputMain = scanner.nextLine();
		    switch(inputMain) {
		    
		    	case "1":
		    		this.callCaseOne();
		    		break;
		    		
		    	case "2":
		    		this.callCaseTwo();
		    		break;
		    		
		    	case "3":
		    		this.callCaseThree();
		    		break;
		    		
		    	case "4":
		    		this.callCaseFour();
		    		break;
		    		
		    	case "5":
		    		this.callCaseFive();
		    		break;
		    		
		    	case "6":
		    		this.callCaseSix();
		    		break;
		    		
		    	case "7":
		    		this.callCaseSeven();
		    		break;
		    		
		    	case "8":
		    		this.callCaseEight();
		    		break;
		    		
		    	default:
		    		System.out.println(inputMain + " n'est pas une sélection valide.");
		    }
	    
		} while (!quit);
		
	}
	
	public void callCaseOne() {
		String inputNewPlace = scanner.nextLine();
		Place newPlace = new Place(inputNewPlace);
		placeDao.createPlace(newPlace);		    		
		System.out.println(menuConstants.getAddedPlace() + newPlace.getId());
	}
	
	public void callCaseTwo() throws NumberFormatException, EntityNotFoundException {
		System.out.println(menuConstants.getFindPlace());
		this.displayAllPlaces(placeDao);
		String inputFoundPlace = scanner.nextLine();
		Place foundPlace = placeDao.findPlaceById(Long.parseLong(inputFoundPlace));
		System.out.println(foundPlace);
	}
	
	public void callCaseThree() throws NumberFormatException, EntityNotFoundException {
		System.out.println(menuConstants.getEditPlace());
		this.displayAllPlaces(placeDao);
		String inputEditedPlace = scanner.nextLine();
		Place editedPlace = placeDao.findPlaceById(Long.parseLong(inputEditedPlace));
		System.out.println(menuConstants.getEditQuestion());
		String inputNewName = scanner.nextLine();
		editedPlace.setName(inputNewName);
		placeDao.updatePlace(editedPlace);
		System.out.println(menuConstants.getEditedPlace());
	}
	
	public void callCaseFour() throws NumberFormatException, EntityNotFoundException {
		System.out.println(menuConstants.getRemovePlace());
		String inputRemovedPlace = scanner.nextLine();
		Place removedPlace = placeDao.findPlaceById(Long.parseLong(inputRemovedPlace));
		boolean placeHasTrips = placeDao.findIfPlaceHasTrips(removedPlace.getId());
		if (placeHasTrips) {
			System.out.println(menuConstants.getRemovePlaceWarning());
			String inputYesNo = scanner.nextLine();
				if (inputYesNo.equals("yes")) {
					tripDao.removeAllTrips(removedPlace);
					placeDao.removePlace(removedPlace);
					System.out.println(menuConstants.getRemovedPlace());
				}
			
		} else {
			placeDao.removePlace(removedPlace);
			System.out.println(menuConstants.getRemovedPlace());
		}
	}
	
	public void callCaseFive() throws NumberFormatException, EntityNotFoundException {
		Trip newTrip = new Trip();
		System.out.println(menuConstants.getDeparture());
		String inputDeparture = scanner.nextLine();
		Place departure = placeDao.findPlaceById(Long.parseLong(inputDeparture));
		System.out.println(menuConstants.getName() + departure.getName());
		System.out.println(menuConstants.getArrival());
		String inputArrival = scanner.nextLine();
		Place arrival = placeDao.findPlaceById(Long.parseLong(inputArrival));
		System.out.println(menuConstants.getName() + arrival.getName());
		System.out.println(menuConstants.getPrice());
		String inputPrice = scanner.nextLine();
		newTrip.setDeparture(departure);
		newTrip.setArrival(arrival);
		newTrip.setPrice(Float.parseFloat(inputPrice));
		tripDao.createTrip(newTrip);		    		
		System.out.println(menuConstants.getAddedTrip() + newTrip.getId());
	}
	
	public void callCaseSix() throws NumberFormatException, EntityNotFoundException {
		System.out.println(menuConstants.getFindTrip());
		this.displayAllTrips(tripDao);
		String inputFoundTrip = scanner.nextLine();
		Trip foundTrip = tripDao.findTripById(Long.parseLong(inputFoundTrip));
		System.out.println("Departure: " + foundTrip.getDeparture() + " - Arrival: " + foundTrip.getArrival() + " - Price: " + foundTrip.getPrice());
	}
	
	public void callCaseSeven() throws NumberFormatException, EntityNotFoundException {
		System.out.println(menuConstants.getRemoveTrip());
		String inputRemovedTrip = scanner.nextLine();
		Trip removedTrip = tripDao.findTripById(Long.parseLong(inputRemovedTrip));
		tripDao.removeTrip(removedTrip);
		System.out.println(menuConstants.getRemovedTrip());
	}
	
	public void callCaseEight() {
		System.out.println(menuConstants.getQuit());
		quit = true;
	}

	public void displayAllPlaces(PlaceDao placeDao) {
		for (Place place : placeDao.findAllPlaces()) {
			System.out.println(place.getId() + " - " + place.getName());
		}
	}
	
	public void displayAllTrips(TripDao tripDao) {
		for (Trip trip : tripDao.findAllTrips()) {
			System.out.println(trip.getId() + " - " + trip.getDeparture() + " / " + trip.getArrival());
		}
	}

}
