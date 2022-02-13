package br.com.bitencourt.plenary.rest.api.model.exception;

import java.io.Serializable;

public class ObjectExistsException extends RuntimeException implements Serializable {

    public ObjectExistsException(String message) {
        super(message);
    }
}
