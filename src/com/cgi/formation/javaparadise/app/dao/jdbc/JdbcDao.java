package com.cgi.formation.javaparadise.app.dao.jdbc;

import java.sql.Connection;

import com.cgi.formation.javaparadise.app.util.ConnectionManager;

public abstract class JdbcDao<T, ID> {
	
    protected Connection connection;

    public JdbcDao() {
        this.connection = ConnectionManager.getInstance();
    }

}
