package com.cgi.formation.javaparadise.app.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cgi.formation.javaparadise.app.dao.TripDao;
import com.cgi.formation.javaparadise.app.exception.EntityNotFoundException;
import com.cgi.formation.javaparadise.app.model.Place;
import com.cgi.formation.javaparadise.app.model.Trip;

public class JdbcTripDao extends JdbcDao<Trip, Long> implements TripDao {

	@Override
	public Long createTrip(Trip trip) {
		String query = "INSERT INTO TRIP (departure_id, arrival_id, trip_price) VALUE (?, ?, ?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, trip.getDeparture().getId());
			ps.setLong(2, trip.getArrival().getId());
			ps.setFloat(3, trip.getPrice());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					trip.setId(rs.getLong(1));
				}
				rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return trip.getId();

	}

	@Override
	public Trip findTripById(Long tripId) {
		String query = "SELECT t.trip_id as trip_id, t.departure_id as departure_id, d.place_name as departure_name, t.arrival_id as arrival_id, a.place_name as arrival_name, t.trip_price as trip_price\r\n" + 
				"FROM TRIP as t\r\n" + 
				"INNER JOIN PLACE as d ON t.departure_id = d.place_id\r\n" + 
				"INNER JOIN PLACE as a ON t.arrival_id = a.place_id\r\n" + 
				"WHERE t.trip_id = ?";
		Trip trip = null;
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, tripId);
			rs = ps.executeQuery();
			if (rs.next()) {
				Long id = rs.getLong("trip_id");
				Place departure = new Place(rs.getLong("departure_id"), rs.getString("departure_name"));
				Place arrival = new Place(rs.getLong("arrival_id"), rs.getString("arrival_name"));
				Float price = rs.getFloat("trip_price");

				trip = new Trip(id, departure, arrival, price);
			} else {
				throw new EntityNotFoundException(tripId);
			}
			
		} catch (SQLException | EntityNotFoundException e) {
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return trip;
	}

	@Override
	public boolean removeTrip(Trip trip) {
		boolean b = true;
		String query = "DELETE FROM TRIP WHERE trip_id = ?";
        
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, trip.getId());
			ps.executeUpdate();
			ps.close();
            
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		
		return b;
	}
	
	@Override
	public boolean removeAllTrips(Place place) {
		boolean b = true;
		String query = "DELETE FROM TRIP WHERE departure_id = ? OR arrival_id = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, place.getId());
			ps.setLong(2, place.getId());
			ps.executeUpdate();
			ps.close();
            
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		
		return b;
		
	}

	@Override
	public List<Trip> findAllTrips() {
		List<Trip> trips = null;
		
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT t.trip_id as trip_id, t.departure_id as departure_id, d.place_name as departure_name, t.arrival_id as arrival_id, a.place_name as arrival_name, t.trip_price as trip_price\r\n" + 
					"FROM TRIP as t\r\n" + 
					"INNER JOIN PLACE as d ON t.departure_id = d.place_id\r\n" + 
					"INNER JOIN PLACE as a ON t.arrival_id = a.place_id\r\n";
			ResultSet rs = statement.executeQuery(query);

			trips = new ArrayList<>();

			while (rs.next()) {
				Long id = rs.getLong("trip_id");
				Place departure = new Place(rs.getLong("departure_id"), rs.getString("departure_name"));
				Place arrival = new Place(rs.getLong("arrival_id"), rs.getString("arrival_name"));
				Float price = rs.getFloat("trip_price");

				Trip trip = new Trip(id, departure, arrival, price);

				trips.add(trip);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return trips;
	}

}
