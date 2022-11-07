package com.bounteous.model;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("serial")
public class ApplicationError implements Serializable {

    public final UUID reference_id = UUID.randomUUID();
    private final int statusCode;
    private final String message;
    public final String infoUrl;

    public ApplicationError(int statusCode, String message) {
        this(statusCode, message, null);
    }

    public ApplicationError(int statusCode, String message, String infoUrl) {
        this.statusCode = statusCode;
        this.message = message;
        this.infoUrl = infoUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ApplicationError i = (ApplicationError) obj;
        return new EqualsBuilder().appendSuper(super.equals(obj))
                .append(statusCode, i.statusCode)
                .append(message, i.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference_id);
    }
}
