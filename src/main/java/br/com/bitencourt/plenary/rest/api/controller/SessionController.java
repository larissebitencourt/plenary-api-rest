package br.com.bitencourt.plenary.rest.api.controller;

import br.com.bitencourt.plenary.rest.api.exception.StandardError;
import br.com.bitencourt.plenary.rest.api.model.MeetingModel;
import br.com.bitencourt.plenary.rest.api.service.SessionService;
import br.com.bitencourt.plenary.rest.api.model.SessionModel;
import br.com.bitencourt.plenary.rest.api.model.dto.SessionModelDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SessionController {

    @Autowired
    private SessionService service;

    @ApiOperation(value = "Open session")
    @PostMapping(path = "/api/v1/sessions")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Create session", response = SessionModel.class),
            @ApiResponse(code = 400, message = "", response = StandardError.class),
            @ApiResponse(code = 401, message = "", response = StandardError.class),
            @ApiResponse(code = 404, message = "", response = StandardError.class),
            @ApiResponse(code = 409, message = "", response = StandardError.class),
            @ApiResponse(code = 422, message = "", response = StandardError.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<SessionModel> save(@RequestBody @Valid SessionModelDTO session) {
        SessionModel sessionModel = service.createSession(session);
        return new ResponseEntity(sessionModel, HttpStatus.CREATED);
    }
}