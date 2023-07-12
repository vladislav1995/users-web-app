package com.example.graphql.user.provider;

import com.example.graphql.provider.AbstractSchemaProvider;
import com.example.graphql.user.resolver.MutationResolver;
import com.example.graphql.user.resolver.QueryResolver;
import graphql.schema.DataFetcher;
import graphql.schema.TypeResolver;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * @author Uladik
 */
@Component
public class SchemaProvider extends AbstractSchemaProvider {

    @Autowired
    private QueryResolver queryResolver;

    @Autowired
    private MutationResolver mutationResolver;

    @Autowired
    public SchemaProvider(@Qualifier("schemaFileName") String schemaFileName) throws IOException {
        super(schemaFileName);
    }

    protected RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                    .dataFetcher("getUserById", queryResolver.getUser())
                    .dataFetcher("getUserByRating", queryResolver.getUserByRating()))
                .type(newTypeWiring("Mutation")
                    .dataFetcher("putUser", mutationResolver.putUser()))
                .build();
    }

}
