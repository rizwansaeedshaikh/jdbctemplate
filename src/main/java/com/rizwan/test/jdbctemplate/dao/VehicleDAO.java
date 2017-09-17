package com.rizwan.test.jdbctemplate.dao;

import java.util.List;

import com.rizwan.test.jdbctemplate.model.Vehicle;

public interface VehicleDAO {

	public void insertVehicle(Vehicle vehicle);
	public void insertBatch(List<Vehicle> vehicles);
	public Vehicle findVehicleByNo(String vehicleNo);
	public List<Vehicle> findVehicles(String[] vehicleNos);
}
