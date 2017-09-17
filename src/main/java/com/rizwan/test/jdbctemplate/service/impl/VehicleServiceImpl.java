package com.rizwan.test.jdbctemplate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rizwan.test.jdbctemplate.dao.VehicleDAO;
import com.rizwan.test.jdbctemplate.model.Vehicle;
import com.rizwan.test.jdbctemplate.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService{

	@Autowired
	VehicleDAO vehicleDao;
	
	@Override
	public void insertVehicle(Vehicle vehicle) {
		
		this.vehicleDao.insertVehicle(vehicle);
	}

	@Override
	public void insertBatch(List<Vehicle> vehicles) {
		
		this.vehicleDao.insertBatch(vehicles);
	}

	@Override
	public Vehicle findVehicleByNo(String vehicleNo) {
		 
		return vehicleDao.findVehicleByNo(vehicleNo);
	}
	
	@Override
	public List<Vehicle> findVehicles(String[] vehicleNos){
		
		return vehicleDao.findVehicles(vehicleNos);
	}
}
