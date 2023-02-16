package com.ocs.client;

import java.io.FileReader;
import java.util.Scanner;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ClientApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			
			Scanner in = new Scanner(System.in);

			System.out.println("Please input file direction ");

			String s = in.nextLine();
			
			var parser = new JSONParser();
			var jsonObj = (JSONObject)parser.parse(new FileReader(s));

			var uri = "http://localhost:9090/OCSAssessment/rest/";

			var client = ClientBuilder.newClient();
			var target = client.target(uri).path("sendInstructions");
			var response = target.request().post(Entity.entity(jsonObj, MediaType.APPLICATION_JSON), String.class);   
			
	        System.out.println(response);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception thrown. Please verify your input.");
		}
	}

}
