package br.com.bitencourt.plenary.rest.api.exception;

import br.com.bitencourt.plenary.rest.api.model.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest) {
        var error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ObjectExistsException.class)
    public ResponseEntity<StandardError> ObjectExists(ObjectExistsException objectExistsException, HttpServletRequest httpServletRequest) {
        var error = new StandardError(LocalDateTime.now(), HttpStatus.CONFLICT.value(), objectExistsException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(ObjectUnauthorizedException.class)
    public ResponseEntity<StandardError> ObjectUnauthorized(ObjectUnauthorizedException objectUnauthorizedException, HttpServletRequest httpServletRequest) {
        var error = new StandardError(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), objectUnauthorizedException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ObjectInvalidException.class)
    public ResponseEntity<StandardError> ObjectInvalid(ObjectInvalidException objectInvalidException, HttpServletRequest httpServletRequest) {
        var error = new StandardError(LocalDateTime.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), objectInvalidException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(ApiIntegrationException.class)
    public ResponseEntity<StandardError> ApiIntegration(ApiIntegrationException apiIntegrationException, HttpServletRequest httpServletRequest) {
        var error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), apiIntegrationException.getMessage(), httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}



