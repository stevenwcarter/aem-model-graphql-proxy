package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

public class AEMButton extends AEMComponent {

    private String text;
    private String link;
    private String accessibilityLabel;

    @GraphQLQuery
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @GraphQLQuery
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @GraphQLQuery
    public String getAccessibilityLabel() {
        return accessibilityLabel;
    }

    public void setAccessibilityLabel(String accessibilityLabel) {
        this.accessibilityLabel = accessibilityLabel;
    }
}
