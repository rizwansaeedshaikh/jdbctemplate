package com.rizwan.test.jdbctemplate.model;

public class Vehicle {

	private String vehicleNo;
	private String color;
	private int wheel;
	private int seat;

	/**
	 * @param vehicleNo
	 *            the vehicleNo to set
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @param wheel
	 *            the wheel to set
	 */
	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	/**
	 * @param seat
	 *            the seat to set
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}

	/**
	 * @return the vehicleNo
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @return the wheel
	 */
	public int getWheel() {
		return wheel;
	}

	/**
	 * @return the seat
	 */
	public int getSeat() {
		return seat;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleNo=" + vehicleNo + ", color=" + color + ", wheel=" + wheel + ", seat=" + seat + "]";
	}

}
