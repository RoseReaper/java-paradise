package com.cgi.formation.javaparadise.app;

import com.cgi.formation.javaparadise.app.exception.EntityNotFoundException;

public class Launcher {
	
	public static void main(String[] args) throws EntityNotFoundException {
		
		Interaction interactionDao = new Interaction();
		
		interactionDao.startInteraction();
		
	}

}
