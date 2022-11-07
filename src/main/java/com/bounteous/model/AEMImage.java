package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.LinkedList;

public class AEMImage extends AEMComponent {

    private String src;
    private String srcUriTemplate;
    private LinkedList<Object> areas;
    private String uuid;
    private boolean lazyEnabled;
    private LinkedList<Object> widths;

    @GraphQLQuery
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @GraphQLQuery
    public String getSrcUriTemplate() {
        return srcUriTemplate;
    }

    public void setSrcUriTemplate(String srcUriTemplate) {
        this.srcUriTemplate = srcUriTemplate;
    }

    @GraphQLQuery
    public LinkedList<Object> getAreas() {
        return areas;
    }

    public void setAreas(LinkedList<Object> areas) {
        this.areas = areas;
    }

    @GraphQLQuery
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @GraphQLQuery
    public boolean isLazyEnabled() {
        return lazyEnabled;
    }

    public void setLazyEnabled(boolean lazyEnabled) {
        this.lazyEnabled = lazyEnabled;
    }

    @GraphQLQuery
    public LinkedList<Object> getWidths() {
        return widths;
    }

    public void setWidths(LinkedList<Object> widths) {
        this.widths = widths;
    }
}
