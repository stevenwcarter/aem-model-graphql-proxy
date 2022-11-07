package com.bounteous.api.inject;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import com.bounteous.service.AEMService;

@Singleton
public class GraphQLProvider implements Provider<GraphQL> {

    private final AEMService aemService;

    @Inject
    public GraphQLProvider(AEMService aemService) {
        this.aemService = aemService;
    }

    @Override
    public GraphQL get() {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withBasePackages("com.bounteous")
                .withOperationsFromSingletons(aemService)
                .generate();

        return new GraphQL.Builder(schema).build();
    }
}
