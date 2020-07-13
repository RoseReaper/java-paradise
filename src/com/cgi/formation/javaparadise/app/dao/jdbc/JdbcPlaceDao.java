package com.cgi.formation.javaparadise.app.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cgi.formation.javaparadise.app.dao.PlaceDao;
import com.cgi.formation.javaparadise.app.exception.EntityNotFoundException;
import com.cgi.formation.javaparadise.app.model.Place;

public class JdbcPlaceDao extends JdbcDao<Place, Long> implements PlaceDao {

	@Override
	public Long createPlace(Place place) {
		String query = "INSERT INTO PLACE (place_name) VALUE (?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, place.getName());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					place.setId(rs.getLong(1));
				}
				rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return place.getId();
	}

	@Override
	public Place findPlaceById(Long placeId) throws EntityNotFoundException {
		String query = "SELECT * FROM PLACE WHERE place_id = ?";
		Place place = null;
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, placeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				Long id = rs.getLong("place_id");
				String name = rs.getString("place_name");

				place = new Place(id, name);
			} else {
				throw new EntityNotFoundException(placeId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return place;
	}
	
	@Override
	public boolean findIfPlaceHasTrips(Long placeId) throws EntityNotFoundException {
		boolean b = true;
		String query = "SELECT COUNT(t.trip_id) as count_trip_id FROM TRIP as t WHERE departure_id = ? OR arrival_id = ?";
		Place place = null;
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, placeId);
			ps.setLong(2, placeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				Long count = rs.getLong("count_trip_id");
				if (count == 0) {
					b = false;
				}

			} else {
				throw new EntityNotFoundException(placeId);
			}
			
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
			
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return b;
		
	}

	@Override
	public boolean updatePlace(Place place) {
		boolean b = true;
		String query = "UPDATE PLACE SET place_name = ? WHERE place_id = ?";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, place.getName());
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
	public boolean removePlace(Place place) {
		boolean b = true;
		String query = "DELETE FROM PLACE WHERE place_id = ?";
        
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, place.getId());
			ps.executeUpdate();
			ps.close();
            
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		
		return b;
	}

	@Override
	public List<Place> findAllPlaces() {
		List<Place> places = null;

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM PLACE";
			ResultSet rs = statement.executeQuery(query);

			places = new ArrayList<>();

			while (rs.next()) {
				Long id = rs.getLong("place_id");
				String name = rs.getString("place_name");

				Place place = new Place(id, name);

				places.add(place);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return places;
	}

}
