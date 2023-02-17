package com.ocs.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ocs.api.model.PayloadObject;
import com.ocs.api.model.ResponseObject;
import com.ocs.conf.Messages;
import com.ocs.opr.Executer;

@Path("/sendInstructions")
public class RestResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return Messages.helloWorld;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseObject testResponse(PayloadObject input) {

		var executer = new Executer();
		return executer.run(input);
	}
}