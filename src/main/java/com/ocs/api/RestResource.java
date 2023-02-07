package com.ocs.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ocs.api.model.PayloadObject;
import com.ocs.api.model.ResponseObject;
import com.ocs.business.RobotBusiness;
import com.ocs.model.Robot;
import com.ocs.model.Terrain;

@Path("/sendInstructions")
public class RestResource {

	RobotBusiness rb;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseObject testResponse(PayloadObject input) {

		return executeCommands(input);
	}

	public ResponseObject executeCommands(PayloadObject input) {
		
		var myTerrain = new Terrain(input.getTerrain());
		var myRobot = new Robot(input);
		var rb = new RobotBusiness(myRobot, myTerrain);
		var response = new ResponseObject();

		try {
			response = rb.executeCommands(input.getCommands(), false);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Execution is interrupted: Robot wants to move out the terrain");
		} catch (Exception e) {
			System.out.println("Exception thrown. Please verify your input.");
		}

		return response;

	}

}
