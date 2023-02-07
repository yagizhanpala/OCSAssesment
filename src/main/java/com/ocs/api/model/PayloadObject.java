package com.ocs.api.model;

import com.ocs.model.Position;

public class PayloadObject {
	
	private String[][] terrain;
	
	private int battery;
	
	private String[] commands;
	
	private Position initialPosition;
	
	
	public String[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(String[][] terrain) {
		this.terrain = terrain;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public String[] getCommands() {
		return commands;
	}

	public void setCommands(String[] commands) {
		this.commands = commands;
	}

	public Position getInitialPosition() {
		return initialPosition;
	}

	public void setInitialPosition(Position initialPosition) {
		this.initialPosition = initialPosition;
	}

	
}
