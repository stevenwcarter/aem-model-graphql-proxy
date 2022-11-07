package com.bounteous.serialization;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class SerializerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Gson.class).toProvider(GsonProvider.class).in(Singleton.class);
    }
}
