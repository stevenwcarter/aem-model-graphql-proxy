package com.bounteous.model;

import com.google.gson.annotations.SerializedName;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.types.GraphQLUnion;

@GraphQLUnion(name = "AEMComponent", possibleTypeAutoDiscovery = true)
public class AEMComponent {
    @SerializedName(":type")
    private String type;

    @GraphQLQuery
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
