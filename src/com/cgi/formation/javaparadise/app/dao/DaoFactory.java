package com.cgi.formation.javaparadise.app.dao;

import com.cgi.formation.javaparadise.app.dao.jdbc.JdbcDao;
import com.cgi.formation.javaparadise.app.dao.jdbc.JdbcPlaceDao;
import com.cgi.formation.javaparadise.app.dao.jdbc.JdbcTripDao;

public class DaoFactory {
	
	private DaoFactory() {

    }

    public static JdbcDao getPlaceDao() {
        return new JdbcPlaceDao();
    }

    public static JdbcDao getTripDao() {
        return new JdbcTripDao();
    }

}
