package com.ocs.opr;

import java.io.File;
import java.io.FileReader;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocs.api.model.PayloadObject;
import com.ocs.api.model.ResponseObject;
import com.ocs.client.ResourceLoader;
import com.ocs.conf.Config;
import com.ocs.conf.Messages;

public class Launcher {

	private final String pattern = "/OCSAssessment/rest/*";
	private final String servletName = "jersey-container-servlet";

	public void go() throws LifecycleException {

		tomcatLauncher();
	}

	public void go(String[] s) {

		try {
			var inputFile = s[1];
			var outputFile = s[2];

			var mapper = new ObjectMapper();
			var input = mapper.readValue(new FileReader(inputFile), PayloadObject.class);

			var executer = new Executer();

			ResponseObject response = executer.run(input);

			mapper.writeValue(new File(outputFile), response);

			System.out.println(Messages.executionOver + outputFile);

		}catch(ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException();
		}catch (Exception e) {
			throw new RuntimeException();
		}

	}

	private void tomcatLauncher() throws LifecycleException {

		Tomcat tomcat = new Tomcat();

		int port = Integer.getInteger("PORT", Integer.parseInt(Config.PORT.label));
		var webappDirectory = new File(".").getAbsolutePath();
		var context = tomcat.addWebapp("/", webappDirectory);

		tomcat.setPort(port);
		tomcat.getConnector();
		Tomcat.addServlet(context, servletName, resourceConfig());
		context.addServletMappingDecoded(pattern, servletName);
		tomcat.start();

		System.out
				.println(Messages.serverListening + tomcat.getHost().getName() + ":" + tomcat.getConnector().getPort());

		tomcat.getServer().await();
	}

	private static ServletContainer resourceConfig() {
		return new ServletContainer(new ResourceConfig(new ResourceLoader().getClasses()));
	}

}
