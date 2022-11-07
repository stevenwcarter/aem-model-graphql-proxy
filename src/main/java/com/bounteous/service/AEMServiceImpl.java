package com.bounteous.service;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import com.bounteous.model.AEMComponent;
import com.bounteous.model.AEMPageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class AEMServiceImpl implements AEMService {

    private static final Logger log = LoggerFactory.getLogger(AEMServiceImpl.class);

    private static final Client client = Client.create();
    private static final String AEM_ENDPOINT_BASE = "http://localhost:4502";
    private final Gson gson;

    @Inject
    public AEMServiceImpl(Gson gson ) {
        this.gson = gson;
    }

    protected String addModelJsonToPath(String path) {
        if (path.toLowerCase().endsWith(".model.json")) {
            return path;
        }

        return path+".model.json";
    }

    protected String getJsonStringFromPath(String path) {
        path = addModelJsonToPath(path);

        WebResource webResource = client.resource(AEM_ENDPOINT_BASE+path);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            log.error("Failed for path {} : HTTP error code : {}", path, response.getStatus());
            return null;
        }

        return response.getEntity(String.class);
    }

    @Override
    @GraphQLQuery(name="pageModel")
    public AEMPageModel getPageModel(@GraphQLArgument(name="path") String path) {
        String pathJson = getJsonStringFromPath(path);

        if (pathJson == null) {
            return null;
        }

        return gson.fromJson(pathJson, AEMPageModel.class);
    }

    @Override
    @GraphQLQuery(name="component")
    public AEMComponent getComponent(@GraphQLArgument(name="path") String path) {
        String pathJson = getJsonStringFromPath(path);

        if (pathJson == null) {
            return null;
        }

        return gson.fromJson(pathJson, AEMComponent.class);
    }
}
