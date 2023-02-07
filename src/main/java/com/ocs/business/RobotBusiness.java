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
	DirectionBusiness db = new DirectionBusiness();
	BatteryBusiness bb = new BatteryBusiness();
	TerrainBusiness tb;
	List<String> samples = new ArrayList<String>();
	List<Location> visitedCells = new ArrayList<Location>();

	public RobotBusiness(Robot r, Terrain t) {
		this.myRobot = r;
		this.myTerrain = t;
	}

	public ResponseObject executeCommands(String[] commands, boolean stepBackExecution) {

		lb = new LocationBusiness(myRobot.getLocation());
		tb = new TerrainBusiness(myTerrain.getTerrain());
		
		if (!stepBackExecution) {
			visitedCells.add(setVisitedLocations());
		}

		for (String c : commands) {

			switch (c) {
			case "F":
				checkFCondition(stepBackExecution);
				break;
			case "B":
				myRobot.setLocation(lb.moveBack(myRobot.getFacing()));
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
			myRobot.setBattery(bb.consumeBattery(c, myRobot.getBattery()));
		}

		return mapResponseObject();
	}

	private void checkFCondition(boolean stepBackExecution) {

		var nextPosition = lb.checkForwardPosition(myRobot.getFacing());

		// lets check first next planned move if it is an OK area to move
		if (!isItAnObs(nextPosition[0], nextPosition[1])) {
			// as there is no obstacle, lets move to this position
			myRobot.setLocation(lb.setNewPosition(nextPosition));
			Location l = setVisitedLocations();
			addVisitedLocations(l);
		} else if (!stepBackExecution) {
			// since there is an obstacle, lets review step back movements
			// this stage shouldn't be executed when it is already in step back process
			stepBack();
		}
	}

	private void stepBack() {

		List<String> stepBackCommands = List.of("E,R,F", "E,L,F", "E,L,L,F", "E,B,R,F", "E,B,B,L,F", "E,F,F",
				"E,F,L,F,L,F");

		Location newLocation;
		int i = 0;

		do {
			var commands = stepBackCommands.get(i).split(",");
			executeCommands(commands, true);
			newLocation = myRobot.getLocation();
			i++;

		} while (isItAnObs(newLocation.getX(), newLocation.getY()));

	}

	private boolean isItAnObs(int x, int y) {
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

	private Location setVisitedLocations() {

		Location loc = new Location();
		loc.setX(myRobot.getLocation().getX());
		loc.setY(myRobot.getLocation().getY());

		return loc;

	}

	private boolean canBeAdded(Location location) {

		return !visitedCells.contains(location);

	}
}
