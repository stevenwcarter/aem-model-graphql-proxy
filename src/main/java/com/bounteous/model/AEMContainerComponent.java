package com.bounteous.model;

import com.google.gson.annotations.SerializedName;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AEMContainerComponent extends AEMComponent {
    @SerializedName(":items")
    private LinkedList<AEMComponentListItem> items;

    @SerializedName(":itemsOrder")
    private LinkedList<String> itemsOrder;

    @GraphQLQuery
    public LinkedList<AEMComponentListItem> getItems() {
        return items;
    }

    public void setItems(LinkedList<AEMComponentListItem> items) {
        this.items = items;
    }

    @GraphQLQuery
    public LinkedList<String> getItemsOrder() {
        return itemsOrder;
    }

    public void setItemsOrder(LinkedList<String> itemsOrder) {
        this.itemsOrder = itemsOrder;
    }

    @GraphQLQuery
    public List<String> getChildrenPaths() {
        return getItems().stream().map(AEMComponentListItem::getKey).collect(Collectors.toList());
    }
}
