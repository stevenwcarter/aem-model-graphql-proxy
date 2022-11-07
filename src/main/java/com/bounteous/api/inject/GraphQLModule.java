package com.bounteous.api.inject;

import com.google.inject.AbstractModule;
import graphql.GraphQL;

public class GraphQLModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GraphQL.class)
                .toProvider(GraphQLProvider.class)
                .asEagerSingleton();
    }
}
