package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.List;

public class AEMAllowedComponents {

    private boolean applicable;
    private List<AEMAllowedComponentsItem> components;

    @GraphQLQuery
    public boolean isApplicable() {
        return applicable;
    }

    public void setApplicable(boolean applicable) {
        this.applicable = applicable;
    }

    @GraphQLQuery
    public List<AEMAllowedComponentsItem> getComponents() {
        return components;
    }

    public void setComponents(List<AEMAllowedComponentsItem> components) {
        this.components = components;
    }
}
