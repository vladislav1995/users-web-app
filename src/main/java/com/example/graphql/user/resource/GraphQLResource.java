package com.example.graphql.user.resource;

import com.example.graphql.provider.AbstractSchemaProvider;
import com.example.graphql.request.GraphQLRequestBody;
import com.example.graphql.user.provider.SchemaProvider;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.example.graphql.util.ResponseUtils.patchExecutionResult;

/**
 * @author Uladik
 */
@Path("/graphql/user")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class GraphQLResource {

    @Autowired
    private AbstractSchemaProvider provider;

    @POST
    @Path("/")
    public Response graphql(GraphQLRequestBody graphQLRequestBody) {
        return Response.ok(executeGraphQLQuery(graphQLRequestBody)).build();
    }

    @GET
    @Path("/")
    public Response graphqlGet(@BeanParam GraphQLRequestBody graphQLRequestBody) {
        return Response.ok(executeGraphQLQuery(graphQLRequestBody)).build();
    }

    private Object executeGraphQLQuery(GraphQLRequestBody body) {
        ExecutionResult result = provider.getGraphQL().execute(convertRequestBody(body));
        return patchExecutionResult(result);
    }

    public void setProvider(SchemaProvider provider) {
        this.provider = provider;
    }

    private ExecutionInput convertRequestBody(GraphQLRequestBody body) {
        return new ExecutionInput.Builder()
                .operationName(body.getOperationName())
                .query(body.getQuery())
                .variables(body.getVariables())
                .build();
    }
}
