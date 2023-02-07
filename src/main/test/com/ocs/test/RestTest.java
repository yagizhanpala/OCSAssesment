package com.ocs.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;

import com.ocs.api.RestResource;
import com.ocs.api.model.PayloadObject;
import com.ocs.api.model.ResponseObject;
import com.ocs.model.Location;
import com.ocs.model.Position;

public class RestTest extends JerseyTest {

	public class GreetingsResourceIntegrationTest extends JerseyTest {

		@Override
		protected Application configure() {
			return new ResourceConfig(RestResource.class);
		}

		@Test
		public void test_case1() {

			String[][] terrain = { { "Fe", "Fe", "Se" }, { "W", "Si", "Obs" }, { "W", "Obs", "Zn" } };

			String[] commands = { "F", "S", "R", "F", "S", "R", "F", "L", "F", "S" };

			Position initialPosition = new Position() {
				{
					setFacing("East");
					setLocation(new Location(0, 0));
				}
			};

			var jsonObj = new PayloadObject() {
				{
					setTerrain(terrain);
					setBattery(50);
					setCommands(commands);
					setInitialPosition(initialPosition);
				}
			};

			List<Location> visitedCells = new ArrayList<Location>() {
				{
					add(new Location(0, 0));
					add(new Location(1, 0));
					add(new Location(1, 1));
					add(new Location(0, 1));
					add(new Location(0, 2));
				}
			};

			Position finalPosition = new Position() {
				{
					setFacing("South");
					setLocation(new Location(0, 2));
				}
			};

			List<String> samples = new ArrayList<String>() {
				{
					add("Fe");
					add("Si");
					add("W");
				}
			};

			ResponseObject expectedResponse = new ResponseObject() {
				{
					setVisitedCells(visitedCells);
					setBattery(8);
					setSamplesCollected(samples);
					setFinalPosition(finalPosition);
				}
			};

			ResponseObject response = target("sendInstructions").request(MediaType.APPLICATION_JSON)
					.post(Entity.entity(jsonObj, MediaType.APPLICATION_JSON), ResponseObject.class);

			assertEquals(expectedResponse, response);
		}
	}

}
