package com.miniq.user.provider;

import com.miniq.example.graphql.provider.AbstractSchemaProvider;
import com.miniq.user.resolver.MutationResolver;
import com.miniq.user.resolver.QueryResolver;
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
                    .dataFetcher("getUserById", queryResolver.getUser()))
                .type(newTypeWiring("Mutation")
                    .dataFetcher("putUser", mutationResolver.putUser()))
                .build();
    }

}
