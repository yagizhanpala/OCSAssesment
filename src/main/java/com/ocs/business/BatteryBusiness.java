package com.ocs.business;

import java.util.Map;

public class BatteryBusiness {
	
	private final Map<String, Integer> batteryConsumption = Map.of("F", 3, "B", 3, "L", 2, "R", 2, "S", 8, "E", 1);
	private final Map<String, Integer> batteryCharge =  Map.of("E", 10);
	
	public int consumeBattery(String command, int current) {
		return current - batteryConsumption.get(command);
	}
	
	public int chargeBattery(String command, int current) {
		return current + batteryCharge.get(command);
	}
}
