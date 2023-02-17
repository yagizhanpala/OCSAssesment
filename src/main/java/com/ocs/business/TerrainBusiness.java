package com.ocs.business;

import java.util.List;

import com.ocs.model.Location;

public class TerrainBusiness {

	private String[][] terrain;

	public TerrainBusiness(String[][] terrain) {
		this.terrain = terrain;
	}

	public List<String> addSample(List<String> samples, Location l) {

		var terrain = getTerrain(l.getX(), l.getY());
		samples.add(terrain);
		return samples;
	}

	public String getTerrain(int cordX, int cordY) {
		return terrain[cordY][cordX];
	}
}
