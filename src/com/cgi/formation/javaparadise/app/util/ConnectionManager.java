package com.cgi.formation.javaparadise.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static Connection CONNECTION_SINGLETON;
    private static final String URL = "jdbc:mysql://localhost:3306/javaparadise?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String USER_PASSWORD = "assbutt";

    private ConnectionManager() {
    	
    }

    public final static Connection getInstance() {
        if(CONNECTION_SINGLETON == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver MySQL introuvable");
            }

            try {
                CONNECTION_SINGLETON = DriverManager.getConnection(URL, USER, USER_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CONNECTION_SINGLETON;
    }

    public final static void close(){
        try {
            if(CONNECTION_SINGLETON == null && !CONNECTION_SINGLETON.isClosed()){
                CONNECTION_SINGLETON.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
