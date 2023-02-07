package com.ocs.api.model;

import java.util.ArrayList;
import java.util.List;

import com.ocs.model.Location;
import com.ocs.model.Position;

public class ResponseObject {
	
	private List<String> SamplesCollected;
	
	private int Battery;
	
	private Position FinalPosition;
	
	private List<Location> VisitedCells;
	

	public List<String> getSamplesCollected() {
		return SamplesCollected;
	}

	public void setSamplesCollected(List<String> samplesCollected) {
		SamplesCollected = samplesCollected;
	}

	public int getBattery() {
		return Battery;
	}

	public void setBattery(int battery) {
		Battery = battery;
	}

	public Position getFinalPosition() {
		return FinalPosition;
	}

	public void setFinalPosition(Position finalPosition) {
		FinalPosition = finalPosition;
	}

	public List<Location> getVisitedCells() {
		return VisitedCells;
	}

	public void setVisitedCells(List<Location> visitedCells) {
		VisitedCells = visitedCells;
	}
	
	
}
