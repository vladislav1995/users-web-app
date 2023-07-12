package com.example.graphql.user.application;

import com.example.graphql.user.resource.GraphQLResource;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Uladik
 */
public class Application extends ResourceConfig {

    public Application() {
        register(GraphQLResource.class);
    }

}
