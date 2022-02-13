package br.com.bitencourt.plenary.rest.api.controller;

import br.com.bitencourt.plenary.rest.api.exception.StandardError;
import br.com.bitencourt.plenary.rest.api.model.MeetingModel;
import br.com.bitencourt.plenary.rest.api.model.VoteModel;
import br.com.bitencourt.plenary.rest.api.model.dto.VoteModelDTO;
import br.com.bitencourt.plenary.rest.api.model.dto.VoteResponseDTO;
import br.com.bitencourt.plenary.rest.api.service.VoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class VoteController {

    @Autowired
    private VoteService service;

    @ApiOperation(value = "Insert vote", code = 201, response = VoteModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Inserted vote", response = VoteModel.class),
            @ApiResponse(code = 400, message = "Bad Request", response = StandardError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = StandardError.class),
            @ApiResponse(code = 404, message = "Not Found", response = StandardError.class),
            @ApiResponse(code = 409, message = "Conflict", response = StandardError.class),
            @ApiResponse(code = 422, message = "Unprocessable", response = StandardError.class)
    })
    @PostMapping(path = "/api/v1/votes")
    public ResponseEntity<VoteModel> save(@RequestBody @Valid VoteModelDTO voteModelDTO) {
        VoteModel voteModel = service.createVote(voteModelDTO);
        return new ResponseEntity(voteModel, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get votes meeting")
    @GetMapping(path = "api/v1/votes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Result ok", response = VoteResponseDTO.class),
            @ApiResponse(code = 400, message = "", response = StandardError.class),
            @ApiResponse(code = 401, message = "", response = StandardError.class),
            @ApiResponse(code = 404, message = "", response = StandardError.class),
            @ApiResponse(code = 409, message = "", response = StandardError.class),
            @ApiResponse(code = 422, message = "", response = StandardError.class)
    })
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<VoteResponseDTO> getVotesMeeting(@RequestParam("meetingId") Integer meetingId) {
        VoteResponseDTO VoteResponseDTO = service.getVotesSessionMeeting(meetingId);
        return new ResponseEntity(VoteResponseDTO, HttpStatus.OK);
    }
}
