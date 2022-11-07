package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AEMAllowedComponentsItem {

    private String path;
    private String title;

    @GraphQLQuery
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @GraphQLQuery
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
