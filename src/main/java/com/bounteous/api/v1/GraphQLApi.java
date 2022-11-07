package com.bounteous.api.v1;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Singleton
@Path("/graphql")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class GraphQLApi {

    private static final Logger log = LoggerFactory.getLogger(GraphQLApi.class);

    private class GraphQLRequestObject {
        public String query;
        public String operationName;
        public Map<String, Object> variables;
    }

    private final GraphQL graphQL;

    @Inject
    public GraphQLApi(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Object> graphql(GraphQLRequestObject request, @Context HttpServletRequest raw, String requestBody) {
        log.debug("request object {}", request);

        ExecutionResult executionResult = request.variables != null ? graphQL.execute(ExecutionInput.newExecutionInput()
                                                                                                    .query(request.query)
                                                                                                    .operationName(
                                                                                                            request.operationName)
                                                                                                    .variables(request.variables)
                                                                                                    .context(raw)
                                                                                                    .build()) : graphQL.execute(
                ExecutionInput.newExecutionInput().query(request.query).operationName(request.operationName)
                              .context(raw).build());

        return executionResult.toSpecification();
    }
}
