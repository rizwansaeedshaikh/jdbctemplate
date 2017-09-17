package com.rizwan.test.jdbctemplate.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.rizwan.test.jdbctemplate.dao.VehicleDAO;
import com.rizwan.test.jdbctemplate.model.Vehicle;

@Repository
public class VehicleDAOImpl implements VehicleDAO {

	@Autowired
	DataSource dataSource;

	@Value("${db.schema}")
	private String schema;

	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @param schema
	 *            the schema to set
	 */
	public void setSchema(String schema) {
		System.out.println("Setting schema to " + schema);
		this.schema = schema;
	}

	@Override
	public void insertVehicle(final Vehicle vehicle) {

		System.out.println("schema: " + schema);

		JdbcTemplate template = new JdbcTemplate(dataSource);
		// template.update(new InsertVehicleStatementCreator(vehicle,
		// this.schema));
		/*
		 * template.update(new PreparedStatementCreator() {
		 * 
		 * @Override public PreparedStatement createPreparedStatement(Connection
		 * con) throws SQLException {
		 * 
		 * String sql = "INSERT INTO " + schema +
		 * ".\"Vehicle\"(\"SEAT\", \"WHEEL\", \"COLOR\") VALUES (?, ?, ?)";
		 * 
		 * PreparedStatement statement = con.prepareStatement(sql);
		 * statement.setInt(1, vehicle.getSeat()); statement.setInt(2,
		 * vehicle.getWheel()); statement.setString(3, vehicle.getColor());
		 * return statement; } });
		 */

		/*
		 * String sql = "INSERT INTO " + schema +
		 * ".\"Vehicle\"(\"SEAT\", \"WHEEL\", \"COLOR\") VALUES (?, ?, ?)";
		 * template.update(sql,new PreparedStatementSetter() {
		 * 
		 * @Override public void setValues(PreparedStatement ps) throws
		 * SQLException {
		 * 
		 * ps.setString(3, vehicle.getColor()); ps.setInt(1, vehicle.getSeat());
		 * ps.setInt(2, vehicle.getWheel()); } });
		 */

		String sql = "INSERT INTO " + schema + ".\"Vehicle\"(\"SEAT\", \"WHEEL\", \"COLOR\") VALUES (?, ?, ?)";
		System.out.println(template.update(sql, vehicle.getSeat(), vehicle.getWheel(), vehicle.getColor()));
		System.out.println(
				template.update(sql, new Object[] { vehicle.getSeat(), vehicle.getWheel(), vehicle.getColor() },
						new int[] { java.sql.Types.INTEGER, java.sql.Types.INTEGER, java.sql.Types.VARCHAR }));
	}

	@Override
	public void insertBatch(final List<Vehicle> vehicles) {

		JdbcTemplate template = new JdbcTemplate(dataSource);

		String sql = "INSERT INTO " + schema + ".\"Vehicle\"(\"SEAT\", \"WHEEL\", \"COLOR\") VALUES (?, ?, ?)";
		template.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setInt(1, vehicles.get(i).getSeat());
				ps.setInt(2, vehicles.get(i).getWheel());
				ps.setString(3, vehicles.get(i).getColor());
			}

			@Override
			public int getBatchSize() {

				return vehicles.size();
			}
		});

	}

	@Override
	public Vehicle findVehicleByNo(String vehicleNo) {

		String sql = "SELECT v.\"VEHICLE_NO\", v.\"COLOR\", v.\"SEAT\", v.\"WHEEL\" from " + this.schema
				+ ".\"Vehicle\" v WHERE \"VEHICLE_NO\" IN ('2','3')";

		JdbcTemplate template = new JdbcTemplate(dataSource);

		final Vehicle vehicle = new Vehicle();
		
		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				System.out.println("Inside processrow" + rs.getType());
				
				vehicle.setWheel(rs.getInt("WHEEL"));
				vehicle.setVehicleNo(rs.getString("VEHICLE_NO"));
				vehicle.setColor(rs.getString("COLOR"));
				vehicle.setSeat(rs.getInt("SEAT"));

			}
		});

		return vehicle;
	}

	public List<Vehicle> findVehicles(String[] vehicleNos) {

		String sql = "SELECT v.\"VEHICLE_NO\", v.\"COLOR\", v.\"SEAT\", v.\"WHEEL\" from " + this.schema
				+ ".\"Vehicle\" v WHERE \"VEHICLE_NO\" IN ('2','3')";

		JdbcTemplate template = new JdbcTemplate(dataSource);

		final List<Vehicle> vehicles = new ArrayList<Vehicle>();

		template.query(sql, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {

				System.out.println("Inside processrow" + rs.getType());
				Vehicle vehicle = new Vehicle();
				vehicles.add(vehicle);
				vehicle.setWheel(rs.getInt("WHEEL"));
				vehicle.setVehicleNo(rs.getString("VEHICLE_NO"));
				vehicle.setColor(rs.getString("COLOR"));
				vehicle.setSeat(rs.getInt("SEAT"));

			}
		});

		return vehicles;
	}
}
