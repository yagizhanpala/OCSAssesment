package com.ocs.business;

import java.util.Map;

import com.ocs.model.Location;

public class LocationBusiness {

	Location location;
	
	public LocationBusiness(Location l) {
		this.location = l;
	}
	
	private Map<String, int[]> movement = Map.of(
			"East", new int[] { 1, 0 }, 
			"West", new int[] { -1, 0 }, 
			"North", new int[] { 0, -1 }, 
			"South", new int[] { 0, 1 });

	public Location setNewPosition(int[] nextCoordinate) {

		location.setX(nextCoordinate[0]);
		location.setY(nextCoordinate[1]);

		return location;
	}

	// returns the amount of x & y to advance
	// in current scenario it is either 1 or 0
	private int[] getHowLongToAdvance(String facing) {
		return movement.get(facing);
	}

	public int[] checkForwardPosition(String facing) {

		
		int[] lengths = getHowLongToAdvance(facing);

		int[] newPosition = new int[2];
		newPosition[0] = lengths[0] + location.getX();
		newPosition[1] = lengths[1] + location.getY();
		
		return newPosition;
	}
	
	public Location moveBack(String facing) {

		int[] lengths = getHowLongToAdvance(facing);

		location.setX(location.getX() - lengths[0]);
		location.setY(location.getY() - lengths[1]);

		return location;
	}

}
