package com.miniq.user.application;

import com.miniq.user.resource.GraphQLResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Uladik
 */
public class Application extends ResourceConfig {

    public Application() {
        register(GraphQLResource.class);
    }

}
