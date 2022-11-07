package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AEMJson extends AEMComponent {

    private String text;

    @GraphQLQuery
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
