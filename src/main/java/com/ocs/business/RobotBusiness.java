package com.ocs.business;

import java.util.ArrayList;
import java.util.List;

import com.ocs.api.model.ResponseObject;
import com.ocs.model.Location;
import com.ocs.model.Position;
import com.ocs.model.Robot;
import com.ocs.model.Terrain;

public class RobotBusiness {

	Robot myRobot;
	Terrain myTerrain;
	LocationBusiness lb;
	TerrainBusiness tb;
	List<Location> visitedCells;
	DirectionBusiness db = new DirectionBusiness();
	BatteryBusiness bb = new BatteryBusiness();
	List<String> samples = new ArrayList<String>();
	private boolean isStepBackExecution = false;

	public RobotBusiness(Robot robot, Terrain terrain) {
		this.myRobot = robot;
		this.myTerrain = terrain;
		lb = new LocationBusiness(myRobot.getLocation());
		tb = new TerrainBusiness(myTerrain.getTerrain());

		visitedCells = new ArrayList<Location>() {
			{
				add(createLocationObject());
			}
		};
	}

	public ResponseObject executeCommands(String[] commands) {

		itirateCommands(commands);
		var responseObject = mapResponseObject();
		return responseObject;
	}

	private void itirateCommands(String[] commands) {

		for (String c : commands) {

			boolean isNextCoordAnObs = false;

			switch (c) {
				case "F":
					isNextCoordAnObs = examineMovement(c);
					break;
				case "B":
					isNextCoordAnObs = examineMovement(c);
					break;
				case "R":
					myRobot.setFacing(db.calculateNewDirection(myRobot.getFacing(), c));
					break;
				case "L":
					myRobot.setFacing(db.calculateNewDirection(myRobot.getFacing(), c));
					break;
				case "S":
					samples = tb.addSample(samples, myRobot.getLocation());
					break;
				case "E":
					myRobot.setBattery(bb.chargeBattery(c, myRobot.getBattery()));
					break;
			}
			updateBattery(c, isNextCoordAnObs);
		}
	}

	private boolean examineMovement(String command) {

		var coord = lb.calculateNextCoordinate(command, myRobot.getFacing());

		if (!isNextMoveValid(coord)) {
			throw new ArrayIndexOutOfBoundsException();
		}

		var isNextCoordAnObs = isCoordinateAnObs(coord[0], coord[1]);

		// check if next coordinate is a location safe to move
		if (!isNextCoordAnObs) {
			registerMovement(coord);
		} else if (!this.isStepBackExecution) {
			// since there is an obstacle, lets review step back movements
			// this stage shouldn't be executed when it is already in step back process
			stepBack();
		}

		return isNextCoordAnObs;
	}

	private void stepBack() {

		this.isStepBackExecution = true;
		List<String> stepBackCommands = List.of("E,R,F", "E,L,F", "E,L,L,F", "E,B,R,F", "E,B,B,L,F", "E,F,F",
				"E,F,L,F,L,F");

		Location location1, location2;
		int i = 0;

		do {
			var commands = stepBackCommands.get(i).split(",");
			location1 = createLocationObject();
			itirateCommands(commands);
			location2 = createLocationObject();
			i++;

		} while (compareTwoLocations(location1, location2) && i < stepBackCommands.size());

		this.isStepBackExecution = false;
	}

	private boolean compareTwoLocations(Location location1, Location location2) {
		return location1.getX() == location2.getX() && location1.getY() == location2.getY();
	}

	private void registerMovement(int[] coord) {
		myRobot.setLocation(lb.setNewLocation(coord));
		addVisitedLocations(createLocationObject());
	}

	private void updateBattery(String command, boolean isNextCoordAnObs) {
		if (!isNextCoordAnObs) {
			myRobot.setBattery(bb.consumeBattery(command, myRobot.getBattery()));
		}
	}

	private boolean isCoordinateAnObs(int x, int y) throws ArrayIndexOutOfBoundsException {
		return tb.getTerrain(x, y).equals("Obs");
	}

	private ResponseObject mapResponseObject() {

		var finalPosition = new Position();
		finalPosition.setFacing(myRobot.getFacing());
		finalPosition.setLocation(myRobot.getLocation());

		var respObj = new ResponseObject();
		respObj.setBattery(myRobot.getBattery());
		respObj.setFinalPosition(finalPosition);
		respObj.setSamplesCollected(samples);
		respObj.setVisitedCells(visitedCells);

		return respObj;
	}

	private void addVisitedLocations(Location location) {
		if (canBeAdded(location)) {
			visitedCells.add(location);
		}
	}

	/*
	 * new Location object is created using Robot Object's current coordinates.
	 */
	private Location createLocationObject() {
		return new Location(myRobot.getLocation().getX(), myRobot.getLocation().getY());
	}

	private boolean canBeAdded(Location location) {
		return !visitedCells.contains(location);
	}
	
	private boolean isNextMoveValid(int[] coord) {
		return coord[0] >= 0 && coord[0] < myRobot.getTerrain()[0].length && coord[1] >= 0 && coord[1] < myRobot.getTerrain().length;
	}
}
