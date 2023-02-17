package com.ocs.client;

import org.apache.catalina.LifecycleException;
import com.ocs.opr.Launcher;

public class ClientApp {

	public void runApplication(String[] s) throws LifecycleException {

		var launcher = new Launcher();

		switch (s.length) {
		case 1: {
			launcher.go();
			break;
		}
		case 3: {
			launcher.go(s);
			break;
		}
		}
	}

}
