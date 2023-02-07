package com.ocs.model;

public class Terrain {
	

	private String[][] terrain;

	public Terrain(String t[][]) {
		this.terrain = t;
	}
	
	public String[][] getTerrain() {
		return terrain;
	}

	public void setTerrain(String[][] terrain) {
		this.terrain = terrain;
	}

	
}
