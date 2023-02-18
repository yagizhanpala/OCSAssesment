package com.ocs.opr;

import com.ocs.api.model.PayloadObject;
import com.ocs.api.model.ResponseObject;
import com.ocs.business.RobotBusiness;
import com.ocs.model.Robot;
import com.ocs.model.Terrain;

public class Executer {

	public ResponseObject run(PayloadObject input) {

		var myTerrain = new Terrain(input.getTerrain());
		var myRobot = new Robot(input);
		var rb = new RobotBusiness(myRobot, myTerrain);

		return rb.executeCommands(input.getCommands());
	}

}
