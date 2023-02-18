package com.ocs.business;

import java.util.Map;

import com.ocs.model.Location;

public class LocationBusiness {

	Location location;

	public LocationBusiness(Location location) {
		this.location = location;
	}

	private Map<String, int[]> movement = Map.of(
			"East", new int[] { 1, 0 },
			"West", new int[] { -1, 0 },
			"North", new int[] { 0, -1 },
			"South", new int[] { 0, 1 });

	public Location setNewLocation(int[] coordinate) {

		location.setX(coordinate[0]);
		location.setY(coordinate[1]);

		return location;
	}

	// returns the amount of x & y to advance
	// in current scenario it is either 1 or 0
	private int[] getHowLongToAdvance(String facing) {
		return movement.get(facing);
	}

	/*
	 * to be called when command F or B to see which position taken when command is
	 * executed
	 * arguments: arg -> String command, String facing
	 */
	public int[] calculateNextCoordinate(String... arg) {

		int[] lengths = getHowLongToAdvance(arg[1]);

		int[] coordinate = new int[2];

		if (arg[0].equals("F")) {
			coordinate[0] = location.getX() + lengths[0];
			coordinate[1] = location.getY() + lengths[1];
		} else if (arg[0].equals("B")) {
			coordinate[0] = location.getX() - lengths[0];
			coordinate[1] = location.getY() - lengths[1];
		}

		return coordinate;
	}

	public Location moveBack(String facing) {

		int[] lengths = getHowLongToAdvance(facing);

		location.setX(location.getX() - lengths[0]);
		location.setY(location.getY() - lengths[1]);

		return location;
	}

}
