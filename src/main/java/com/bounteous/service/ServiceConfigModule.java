package com.bounteous.service;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by steve on 6/29/16.
 */
public class ServiceConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AEMService.class).to(AEMServiceImpl.class)
                                .in(Singleton.class);
    }

}
