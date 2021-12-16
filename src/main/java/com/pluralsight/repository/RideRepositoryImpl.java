package com.pluralsight.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.RideRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Ride> getRides() {
		
		//Row mapper Anonymous class implementation
		/*
		 * List<Ride> rides = jdbcTemplate.query("select * from ride", new
		 * RowMapper<Ride>() {
		 * 
		 * public Ride mapRow(ResultSet rs, int rowNum) throws SQLException { Ride ride
		 * = new Ride(); ride.setId(rs.getInt("id"));
		 * ride.setName(rs.getString("name")); ride.setDuration(rs.getInt("duration"));
		 * 
		 * return ride; }});
		 */
		
		//Row Mapper separate class implementation	
		List<Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
		
		return rides;
	}

	public Ride createRide(Ride ride) {		
		jdbcTemplate.update("insert into ride (name,duration) values (?,?)", ride.getName(),ride.getDuration());
		
		//Simple JDBC Insert approach on how to insert a record to the database and retrieve the autoincrement value
		/*
		 * SimpleJdbcInsert insertTemplate = new SimpleJdbcInsert(jdbcTemplate);
		 * 
		 * List<String> column = new ArrayList<String>(); column.add("name");
		 * column.add("duration");
		 * 
		 * insertTemplate.setTableName("ride"); insertTemplate.setColumnNames(column);
		 * 
		 * Map<String, Object> data = new HashMap<String, Object>(); data.put("name",
		 * ride.getName()); data.put("duration", ride.getDuration());
		 * 
		 * //To get the autoincrement column value from the data base after inserting
		 * record to the database insertTemplate.setGeneratedKeyName("id");
		 * 
		 * Number key = insertTemplate.executeAndReturnKey(data);
		 * System.out.println("Auto Increment Value ->"+key);
		 */
		
		return null;
	}
	
}
