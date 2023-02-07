package com.ocs.model;

import com.ocs.api.model.PayloadObject;

public class Robot {
	
	private String[][] terrain;
	private Location location;
	private String facing;
	private int battery;
	
	public Robot(PayloadObject input) {
		
		Position position = input.getInitialPosition();

		terrain = input.getTerrain();
		location = position.getLocation();
		facing = position.getFacing();
		battery = input.getBattery();		
	}

	public String[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(String[][] terrain) {
		this.terrain = terrain;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFacing() {
		return facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}
	

}
