package com.ocs.client;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.ocs.api.RestResource;

public class ResourceLoader extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(RestResource.class);
        return classes;
    }
}
