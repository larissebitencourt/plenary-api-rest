package br.com.bitencourt.plenary.rest.api.model.exception;

import java.io.Serializable;

public class ObjectInvalidException extends RuntimeException implements Serializable {

    public ObjectInvalidException(String message) {
        super(message);
    }
}
