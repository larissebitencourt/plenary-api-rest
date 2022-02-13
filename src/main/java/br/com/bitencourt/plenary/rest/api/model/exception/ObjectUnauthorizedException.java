package br.com.bitencourt.plenary.rest.api.model.exception;

import java.io.Serializable;

public class ObjectUnauthorizedException extends RuntimeException implements Serializable {

    public ObjectUnauthorizedException(String message) {
        super(message);
    }
}
