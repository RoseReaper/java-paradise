package com.cgi.formation.javaparadise.app;

public class MenuConstants {
	
	private final String welcome = "Welcome aboard!";
	private final String mainScreen = "\n"
			+ "What would you like to do?\n"
    		+ "1 - Add a place\n"
    		+ "2 - Find a place\n"
    		+ "3 - Edit a place\n"
    		+ "4 - Remove a place\n"
    		+ "5 - Add a trip\n"
    		+ "6 - Find a trip\n"
    		+ "7 - Remove a trip\n"
    		+ "8 - Quit";
	private final String name = "Name:";
	private final String price = "Price:";
	private final String findPlace = "Which place do you want to select?";
	private final String findTrip = "Which trip do you want to select?";
	private final String editPlace = "Which place do you want to edit?";
	private final String editedPlace = "Place edited!";
	private final String editTrip = "Which trip do you want to edit?";
	private final String editedTrip = "Trip edited!";
	private final String editQuestion = "What is the new name ?";
	private final String addedPlace = "Place added with the ID=";
	private final String addedTrip = "Trip added with the ID=";
	private final String departure = "Departure: Please enter the ID of the place:";
	private final String arrival = "Arrival: Please enter the ID of the place:";
	private final String removePlace = "Please enter the ID of the place:";
	private final String removePlaceWarning = "All trips with this Place will be removed with it.\n"
			+ "Are you sure you want to remove it? [yes/no]";
	private final String removedPlace = "Place removed!";
	private final String removeTrip = "Please enter the ID of the trip:";
	private final String removedTrip = "Trip removed!";
	private final String quit = "Bye bye!";

	public String getWelcome() {
		return welcome;
	}

	public String getMainScreen() {
		return mainScreen;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getFindPlace() {
		return findPlace;
	}

	public String getFindTrip() {
		return findTrip;
	}

	public String getEditPlace() {
		return editPlace;
	}

	public String getEditedPlace() {
		return editedPlace;
	}

	public String getEditTrip() {
		return editTrip;
	}

	public String getEditedTrip() {
		return editedTrip;
	}

	public String getEditQuestion() {
		return editQuestion;
	}

	public String getAddedPlace() {
		return addedPlace;
	}
	
	public String getAddedTrip() {
		return addedTrip;
	}
	
	public String getDeparture() {
		return departure;
	}
	
	public String getArrival() {
		return arrival;
	}
	
	public String getRemovePlace() {
		return removePlace;
	}

	public String getRemovePlaceWarning() {
		return removePlaceWarning;
	}

	public String getRemovedPlace() {
		return removedPlace;
	}
	
	public String getRemoveTrip() {
		return removeTrip;
	}
	
	public String getRemovedTrip() {
		return removedTrip;
	}

	public String getQuit() {
		return quit;
	}
	
}
