package br.com.bitencourt.plenary.rest.api.model.exception;

import java.io.Serializable;

public class ApiIntegrationException extends RuntimeException implements Serializable {

    public ApiIntegrationException(String message) {
        super(message);
    }
}

