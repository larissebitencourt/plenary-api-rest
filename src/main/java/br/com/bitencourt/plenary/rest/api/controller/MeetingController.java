package br.com.bitencourt.plenary.rest.api.controller;

import br.com.bitencourt.plenary.rest.api.exception.StandardError;
import br.com.bitencourt.plenary.rest.api.model.MeetingModel;
import br.com.bitencourt.plenary.rest.api.service.MeetingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MeetingController {

    @Autowired
    private MeetingService service;

    @ApiOperation(value = "Create meeting")
    @PostMapping(path = "/api/v1/meetings")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Inserted vote", response = MeetingModel.class),
            @ApiResponse(code = 400, message = "", response = StandardError.class),
            @ApiResponse(code = 401, message = "", response = StandardError.class),
            @ApiResponse(code = 404, message = "", response = StandardError.class),
            @ApiResponse(code = 409, message = "", response = StandardError.class),
            @ApiResponse(code = 422, message = "", response = StandardError.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<MeetingModel> create(@RequestBody @Valid MeetingModel meeting) {
        service.createMeeting(meeting);
        return new ResponseEntity<>(meeting, HttpStatus.CREATED);
    }
}
