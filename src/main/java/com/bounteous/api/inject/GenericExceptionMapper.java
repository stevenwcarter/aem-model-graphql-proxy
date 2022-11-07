package com.bounteous.api.inject;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.bounteous.model.ApplicationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * To prevent returning more embarrassing error messages in the responses, this
 * class will capture all exceptions and map them into a friendlier error message.
 * <p>
 * A reference ID (in UUID format) is generated for each message.  This reference
 * UUID is concatenated with the error when being sent to the logs. When Coke or
 * another vendor is asking about an error response they received, they should
 * provide the reference ID to make searching for the error in our logs easier. This
 * also removes the guesswork around determining which error in the logs should be
 * investigated.
 *
 * @author steve.carter
 */
@javax.ws.rs.ext.Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger log = LoggerFactory.getLogger(GenericExceptionMapper.class);

    private final Gson gson;

    @Inject
    public GenericExceptionMapper(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Response toResponse(Exception exception) {
        ApplicationError error = new ApplicationError(50000, exception.getMessage(), "");

        log.error("{} : {}", exception.getMessage(), error.reference_id, exception);
        Throwable t = exception.getCause();
        if (t != null) {
            log.error("caused by : ", t);
        }

        if (exception instanceof WebApplicationException) {
            Response errorResponse = ((WebApplicationException) exception).getResponse();
            String jsonError = gson.toJson(errorResponse);
            if (errorResponse != null && jsonError != null) {
                log.error(jsonError);
            }
            return Response.serverError()
                    .entity(errorResponse)
                    .build();
        }

        return Response.ok(error)
                .status(400)
                .build();
    }
}
