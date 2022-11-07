package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AEMComponentListItem {

    private String key;
    private AEMComponent component;

    @GraphQLQuery
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @GraphQLQuery
    public AEMComponent getComponent() {
        return component;
    }

    public void setComponent(AEMComponent component) {
        this.component = component;
    }
}
