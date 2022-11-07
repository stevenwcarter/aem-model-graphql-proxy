package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AEMText extends AEMComponent {

    private String text;
    private boolean richText;

    @GraphQLQuery
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @GraphQLQuery
    public boolean isRichText() {
        return richText;
    }

    public void setRichText(boolean richText) {
        this.richText = richText;
    }
}
