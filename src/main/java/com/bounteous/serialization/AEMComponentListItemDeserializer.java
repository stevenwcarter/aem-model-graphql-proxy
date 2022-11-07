package com.bounteous.serialization;

import com.bounteous.model.AEMComponentListItem;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.bounteous.model.AEMComponent;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class AEMComponentListItemDeserializer implements JsonDeserializer<List<AEMComponentListItem>> {
    @Override
    public List<AEMComponentListItem> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<AEMComponentListItem> componentListItems = new LinkedList<>();

        jsonElement.getAsJsonObject().entrySet().forEach(entry -> {
            AEMComponent component = jsonDeserializationContext.deserialize(entry.getValue(), AEMComponent.class);

            AEMComponentListItem componentListItem = new AEMComponentListItem();
            componentListItem.setKey(entry.getKey());
            componentListItem.setComponent(component);

            componentListItems.add(componentListItem);
        });

        return componentListItems;
    }
}
