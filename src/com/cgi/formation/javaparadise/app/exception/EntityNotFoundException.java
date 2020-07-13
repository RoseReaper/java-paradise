package com.cgi.formation.javaparadise.app.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Long id) {
        super("Entity with ID=" + id + " does not exist");
    }
}
