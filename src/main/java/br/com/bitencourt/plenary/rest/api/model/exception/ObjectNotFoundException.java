package br.com.bitencourt.plenary.rest.api.model.exception;

import java.io.Serializable;

public class ObjectNotFoundException extends RuntimeException implements Serializable {

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
