package com.ocs.conf;

public enum Config {
	PORT("8080"),
	APPLICATION_NAME("obs_test");
	
    public final String label;

    private Config(String label) {
        this.label = label;
    }

}
