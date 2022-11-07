package com.bounteous.api.inject;

import com.bounteous.api.filter.JsonFilter;
import com.bounteous.serialization.GsonProvider;
import com.google.gson.Gson;
import com.google.inject.Singleton;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Filters, servlets and other web related changes should be made in this class.
 * This removes the need for a large web.xml file, and provides a lot of power to
 * the routing engine as you can use regular expressions and other advanced methods
 * to configure the routing.  Jersey scans the referenced packages and automatically
 * sets up its API calls.  It should be easy to add additional API versions if the
 * paradigm below is followed.
 * <p>
 * https://github.com/google/guice
 * https://github.com/google/guice/wiki/Servlets
 *
 * @author steve.carter
 */
public class WebServiceConfigModule extends JerseyServletModule {

    private static final Logger log = LoggerFactory.getLogger(WebServiceConfigModule.class);

    @Override
    protected void configureServlets() {
        Map<String, String> mappingParamsV1 = new HashMap<>();
        mappingParamsV1.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.bounteous.api.v1");
        mappingParamsV1.put(PackagesResourceConfig.FEATURE_DISABLE_WADL, "true");

        bind(GuiceContainer.class).in(Singleton.class);

        bind(Gson.class).toProvider(GsonProvider.class).in(Singleton.class);

        bind(GsonMessageBodyHandler.class).in(Singleton.class);

        bind(GenericExceptionMapper.class).in(Singleton.class);

        serve("/*").with(GuiceContainer.class, mappingParamsV1);

        // Automatically add content-type header if the URL ends with .json
        filter("*.json").through(JsonFilter.class);
    }

}
