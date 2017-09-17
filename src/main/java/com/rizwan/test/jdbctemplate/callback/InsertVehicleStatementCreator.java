package com.rizwan.test.jdbctemplate.callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.rizwan.test.jdbctemplate.model.Vehicle;

public class InsertVehicleStatementCreator implements PreparedStatementCreator {

	private String schema;

	/**
	 * @param vehicle
	 *            the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	private Vehicle vehicle;

	public InsertVehicleStatementCreator(Vehicle vehicle, String schema) {
		this.vehicle = vehicle;
		this.schema = schema;
	}

	public InsertVehicleStatementCreator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

		System.out.println("this.schema" + schema);

		String sql = "INSERT INTO " 
				+ "public.\"Vehicle\" (\"COLOR\", \"WHEEL\", \"SEAT\") VALUES(?,?,?)";
		
		System.out.println(sql);

		PreparedStatement statement = connection.prepareStatement(sql);

		//statement.setString(1, vehicle.getVehicleNo());
		statement.setString(1, vehicle.getColor());
		statement.setInt(2, vehicle.getWheel());
		statement.setInt(3, vehicle.getSeat());

		return statement;
	}

}
