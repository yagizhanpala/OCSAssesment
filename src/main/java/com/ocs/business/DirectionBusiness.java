package com.ocs.business;

import java.util.List;

public class DirectionBusiness {

	public final List<String> directions =  List.of("North", "East", "South", "West");

	private String facing;

	/*
	 * REQUIREMENT:
	 *	•	Turn Left (L):  
	 *		o	Consumes 2 battery units. 
	 *		o	Changes the facing direction 90º to the right. ??? -> assumed typo
	 *	•	Turn Right (R): 
	 *		o	Consumes 2 battery units. 
	 *		o	Changes the facing direction 90º to the left. ??? -> assumed typo
	 *
	 */
	public String calculateNewDirection(String facing, String newDirection) {

		this.facing = facing;

		if (newDirection.equals("R")) {
			return turnRight();
		}

		return turnLeft();
	}

	private String turnRight() {
		// get the index of current direction, then add one to move to next by adding
		// mod the size of length in case you were already on the limit of list
		return directions.get((directions.indexOf(this.facing) + 1) % directions.size());
	}

	private String turnLeft() {
		// if we are at first item already, moving to last item
		// which means -in current scenario- we move to West if we are in North
		if (directions.indexOf(facing) == 0) {
			return directions.get(directions.size() - 1);
		}
		return directions.get(directions.indexOf(facing) - 1);
	}
}
