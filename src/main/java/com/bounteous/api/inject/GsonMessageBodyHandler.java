package com.bounteous.api.inject;

import com.google.gson.Gson;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Encapsulates GSON support for custom serializing/deserializing objects.
 * Objects can be directly consumed/returned from the API methods without needing
 * to worry about their (de)serialization.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class GsonMessageBodyHandler implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(GsonMessageBodyHandler.class);

    private static final String CHARSET = "UTF-8";

    private final Gson gson;

    @Context
    public Providers providers;

    @Inject
    public GsonMessageBodyHandler(Gson gson) {
        this.gson = gson;
    }

    public long getSize(final Object t,
                        final Class<?> type,
                        final Type genericType,
                        final Annotation[] annotations,
                        final MediaType mediaType) {
        return -1;
    }

    public boolean isWriteable(final Class<?> type,
                               final Type genericType,
                               final Annotation[] annotations,
                               final MediaType mediaType) {
        return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    public void writeTo(final Object t,
                        final Class<?> type,
                        final Type genericType,
                        final Annotation[] annotations,
                        final MediaType mediaType,
                        final MultivaluedMap<String, Object> httpHeaders,
                        final OutputStream entityStream) throws IOException, WebApplicationException {
        httpHeaders.putSingle(javax.ws.rs.core.HttpHeaders.CONTENT_TYPE, mediaType.toString() + ";charset=UTF-8");
        entityStream.write(gson.toJson(t, type)
                                   .getBytes());
    }

    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public Object readFrom(Class<Object> type,
                           Type genericType,
                           Annotation[] annotations,
                           MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders,
                           InputStream entityStream) throws IOException, WebApplicationException {
        // all the aspects of the system shall encode their content in the UTF-8 encoding.

        try (InputStreamReader streamReader = new InputStreamReader(entityStream, CHARSET)) {
            final Type jsonType;
            if (type.equals(genericType)) {
                jsonType = type;
            } else {
                jsonType = genericType;
            }
            return gson.fromJson(streamReader, jsonType);
        }
    }
}
