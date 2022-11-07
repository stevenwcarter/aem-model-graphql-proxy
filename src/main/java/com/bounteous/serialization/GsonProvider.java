package com.bounteous.serialization;

import com.bounteous.model.AEMComponent;
import com.bounteous.model.AEMComponentListItem;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provider;

import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * Constructs the Gson instance injected everywhere else in the application.
 * Eventually, turning off the serialization of nulls will help reduce payload
 * sizes, especially for the responses that contain items.
 *
 * @author steve.carter
 */
public class GsonProvider implements Provider<Gson> {

    public Gson get() {
        @SuppressWarnings("UnstableApiUsage")

        final Type aemComponentLinkedListType = new TypeToken<LinkedList<AEMComponentListItem>>() {
        }.getType();
        return new GsonBuilder().registerTypeAdapter(AEMComponent.class, new AEMComponentSerializer())
                                .registerTypeAdapter(aemComponentLinkedListType, new AEMComponentListItemDeserializer())
                                .setPrettyPrinting()
                                .serializeNulls()
                                .disableHtmlEscaping()
                                .create();
    }
}
